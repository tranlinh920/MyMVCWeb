package com.my.controller.home;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.my.converter.UserConverter;
import com.my.dto.PageDTO;
import com.my.dto.ProductDTO;
import com.my.dto.ProductTypeDTO;
import com.my.dto.UserDTO;
import com.my.entities.ProductEntity;
import com.my.exception.RecordNotFoundException;
import com.my.paging.PagingComponent;
import com.my.services.PageService;
import com.my.services.ProductService;
import com.my.services.ProductTypeService;
import com.my.services.UserService;
import com.my.utils.RestFacebookUtil;
import com.my.utils.SecurityUtil;

@Controller
public class HomeController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductTypeService productTypeService;

	@Autowired
	private PagingComponent pagingComponent;

	@Autowired
	private RestFacebookUtil restFB;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private PageService pageService;

	@GetMapping(value = { "/", "/trang-chu" })
	public String getHomePage(Model model, HttpSession session) {
		return "home/home";
	}

	@GetMapping("/trang/{pageCode}")
	public String getPage(Model model, @PathVariable("pageCode") String pageCode) {
		PageDTO dto = pageService.findByCode(pageCode);
		model.addAttribute("pageModel", dto);
		return "home/page";
	}

	@GetMapping("/san-pham/{proTypeCode}")
	public String getProcutByTypePage(//
			HttpSession session, //
			Model model, //
			@PathVariable String proTypeCode, //
			@RequestParam(defaultValue = "1") int page, //
			@RequestParam(defaultValue = "8") int limit, //
			@RequestParam(name = "sort_type", required = false) String sortType, //
			@RequestParam(name = "sort_param", required = false) String sortParam //
	) {

		int totalItem = productService.count().intValue();
		int visiblePages = 5;
		boolean entityExitField = ProductEntity.isExitField(sortParam);
		pagingComponent = pagingComponent.doPagingAndSort(//
				page, limit, totalItem, visiblePages, sortType, sortParam, entityExitField);

		ProductTypeDTO typeDTO = productTypeService.findOneByCode(proTypeCode);
		if (typeDTO == null)// khong nen thow exception ma nen chuyen huong sang trang 404
			throw new RecordNotFoundException("Can not found ProductType with code: " + proTypeCode);

		List<ProductDTO> products = productService.findByProType(typeDTO, pagingComponent.getPageable());
		if (products.isEmpty())
			throw new RecordNotFoundException("Can not found ProductEntity with code: " + proTypeCode);

		model.addAttribute("productType", typeDTO);
		model.addAttribute("products", products);
		model.addAttribute("paging", pagingComponent.getPaging());

		return "home/product-by-type";
	}

	@GetMapping("/chi-tiet-san-pham/{proId}")
	public String getProductDetailPage(Model model, @PathVariable Long proId) {
		ProductDTO product = productService.findOne(proId);
		model.addAttribute("product", product);
		return "home/product-detail";
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/gio-hang")
	public String getProductCartPage(Model model, HttpSession session)
			throws JsonGenerationException, JsonMappingException, IOException {
		Map<Long, Integer> numOfProId = new HashMap<Long, Integer>();
		List<ProductDTO> dtos = new ArrayList<ProductDTO>();
		List<Long> proIdList = (List<Long>) session.getAttribute("PRODUCTIDS-CART");

		// Kiểm tra nếu sản phẩm có id trùng nhau thì không thêm vào list nữa.
		if (proIdList != null) {
			ProductDTO dto;
			for (Long proId : proIdList) {
				if (!numOfProId.containsKey(proId)) {
					numOfProId.put(proId, 1);
					dto = productService.findOne(proId);
					dto.setNumOfProBuyed(1); // khởi tạo số lượng mua là 1
					dtos.add(dto);
				} else {
					Integer value = numOfProId.get(proId);
					numOfProId.put(proId, value + 1);

					// Cập nhật số lượng mua cho sản phẩm đã có trong giỏ
					for (int i = 0; i < dtos.size(); i++) {
						dto = dtos.get(i);
						if (dto.getProId() == proId)
							dto.setNumOfProBuyed(dto.getNumOfProBuyed() + 1);
					}
				}
			}
		}

		String productsJson = new ObjectMapper().writeValueAsString(dtos);
		model.addAttribute("numOfProId", numOfProId);
		model.addAttribute("products", dtos);
		model.addAttribute("productsJson", productsJson);
		return "home/checkout";
	}

	@GetMapping("/tim-kiem")
	public String searchByProductName(Model model, //
			@RequestParam("p") String searchString, //
			@RequestParam(defaultValue = "1") int page, //
			@RequestParam(defaultValue = "8") int limit, //
			@RequestParam(name = "sort_type", required = false) String sortType, //
			@RequestParam(name = "sort_param", required = false) String sortParam //
	) {

		int totalItem = productService.countByProNameContaining(searchString).intValue();
		int visiblePages = 5;
		boolean entityExitField = ProductEntity.isExitField(sortParam);
		pagingComponent = pagingComponent.doPagingAndSort(//
				page, limit, totalItem, visiblePages, sortType, sortParam, entityExitField);

		List<ProductDTO> products = productService.findByProNameContaining(searchString, pagingComponent.getPageable());

		model.addAttribute("searchNotify", "Kết quả cho: " + searchString + " ( " + totalItem + " kết quả)");
		model.addAttribute("searchString", searchString);
		model.addAttribute("products", products);
		model.addAttribute("paging", pagingComponent.getPaging());
		return "home/search";
	}

	@GetMapping("/dang-nhap-bang-facebook")
	public String loginFacebook(HttpServletRequest request) {
		com.restfb.types.User user = null;
		UserDetails userDetail;
		try {
			String code = request.getParameter("code");
			String accessToken = "";
			accessToken = restFB.getToken(code); // if get failure throw IOException
			user = restFB.getUserInfo(accessToken);
			userDetail = userDetailsService.loadUserByUsername(user.getId());
		} catch (IOException e) {
			return "login?facebook=error";
		} catch (Exception e) {
			// create new user
			userService.save(userConverter.toDTO(user));
			userDetail = restFB.buildUser(user);
		}

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
				userDetail.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Cập nhật user model
		UserDTO userDto = userService.findByUserName(SecurityUtil.getUserDetails().getUsername());
		request.getSession().setAttribute("user", userDto);

		return "home/home";
//		try {
//			accessToken = restFB.getToken(code);
//		} catch (IOException e) {
//			return "login?facebook=error";
//		}

	}

}
