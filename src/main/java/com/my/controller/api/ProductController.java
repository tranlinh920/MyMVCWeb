package com.my.controller.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.my.dto.ProductDTO;
import com.my.dto.ProductImageDTO;
import com.my.dto.ProductTypeDTO;
import com.my.entities.ProductEntity;
import com.my.models.ProductUpload;
import com.my.models.Result;
import com.my.paging.PagingComponent;
import com.my.services.ProductService;
import com.my.services.ProductTypeService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	ProductTypeService productTypeService;

	@Autowired
	private PagingComponent pagingComponent;

	@GetMapping()
	public ResponseEntity<?> all(//
			@RequestParam(defaultValue = "1") int page, //
			@RequestParam(defaultValue = "10") int limit, //
			@RequestParam(name = "sort_type", required = false) String sortType, //
			@RequestParam(name = "sort_param", required = false) String sortParam // chọn field để sắp xếp
	) {
		int totalItem = productService.count().intValue();
		int visiblePages = 5;
		boolean entityExitField = ProductEntity.isExitField(sortParam);
		pagingComponent = pagingComponent.doPagingAndSort(//
				page, limit, totalItem, visiblePages, sortType, sortParam, entityExitField);

		List<ProductDTO> products = productService.findAll(pagingComponent.getPageable());
		Result<List<ProductDTO>> result = new Result<>(//
				200, products, pagingComponent.getPaging());

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> one(@PathVariable(name = "id") Long proId) {
		ProductDTO product = productService.findOne(proId);
		Result<ProductDTO> result = new Result<>(200, product);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/images/{id}")
	public ResponseEntity<?> images(@PathVariable(name = "id") Long proId) {
		List<ProductImageDTO> images = productService.findImages(proId);
		Result<List<ProductImageDTO>> result = new Result<>(200, images);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<?> newProduct(@ModelAttribute ProductUpload pro, HttpServletRequest request) {
		String uploadPath = request.getServletContext().getRealPath("resources/images/products");
		return productService.save(pro, uploadPath);
	}

	@PostMapping("/{id}")
	public ResponseEntity<?> replaceProduct(@PathVariable(name = "id") Long proId, @ModelAttribute ProductUpload pro,
			HttpServletRequest request) {
		String uploadPath = request.getServletContext().getRealPath("resources/images/products");
		pro.setProId(proId);
		return productService.save(pro, uploadPath);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") Long proId) {
		productService.delete(proId);
		Result<?> result = new Result<>(200, "Delete successful");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked" })
	@PostMapping("/add-to-cart/{id}")
	public ResponseEntity<?> addToCart(@PathVariable("id") Long proId, HttpSession session) {
		try {
			List<Long> proIdList = (List<Long>) session.getAttribute("PRODUCTIDS-CART");
			if (proIdList == null)
				proIdList = new ArrayList<Long>();
			proIdList.add(proId);
			int cartSize = proIdList.size();
			session.setAttribute("PRODUCTIDS-CART", proIdList);
			session.setAttribute("CART-SIZE", cartSize);
			Result<?> result = new Result<>(200, cartSize, "Add successful");
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			Result<?> result = new Result<>(500, "Add field: " + e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}

	@SuppressWarnings({ "unchecked" })
	@DeleteMapping("/del-from-cart")
	public ResponseEntity<?> deleteAllCart(HttpSession session) {
		Result<?> result;
		try {
			List<Long> proIdList = (List<Long>) session.getAttribute("PRODUCTIDS-CART");
			if (proIdList == null) {
				result = new Result<>(500, "Delete field: no product-cart exist");
				return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			proIdList.clear();
			int cartSize = proIdList.size();
			session.removeAttribute("PRODUCTIDS-CART");
			session.removeAttribute("CART-SIZE");
			result = new Result<>(200, cartSize, "Delete successful");
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			result = new Result<>(500, "Delete field: " + e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@SuppressWarnings({ "unchecked" })
	@DeleteMapping("/del-from-cart/{id}")
	public ResponseEntity<?> deleteFromCart(@PathVariable("id") Long proId, HttpSession session) {
		Result<?> result;
		try {
			List<Long> proIdList = (List<Long>) session.getAttribute("PRODUCTIDS-CART");
			if (proIdList == null) {
				result = new Result<>(500, "Delete field: no product-cart exist");
				return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			while (true) {
				if (proIdList.contains(proId))
					proIdList.remove(proId);
				else
					break;
			}

			int cartSize = proIdList.size();
			session.setAttribute("CART-SIZE", cartSize);
			session.setAttribute("PRODUCTIDS-CART", proIdList);
			result = new Result<>(200, cartSize, "Delete successful");
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			result = new Result<>(500, "Delete field: " + e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/product-type")
	public ResponseEntity<?> getRandomByProductType(//
			@RequestParam(defaultValue = "1") int page, //
			@RequestParam(defaultValue = "4") int limit, //
			@RequestParam(name = "sort_type", required = false) String sortType, //
			@RequestParam(name = "sort_param", required = false) String sortParam, //
			@RequestParam(name = "get_random", required = false, defaultValue = "false") boolean randomGetting, //
			@RequestParam(name = "product_type_code", defaultValue = "oc") String proTypeCode// chọn field để sắp xếp
	) {
		int totalItem = productService.countByProType(proTypeCode).intValue();
		int visiblePages = 1;

		if (randomGetting) {
			int totalPages = (int) Math.ceil((double) totalItem / limit);
			int randomPage = (int) (Math.random() * totalPages + 1);
			page = ((randomPage >= totalPages) && (randomPage > 1)) ? (randomPage - 1) : randomPage;
		}

		boolean entityExitField = ProductEntity.isExitField(sortParam);
		pagingComponent = pagingComponent.doPagingAndSort(//
				page, limit, totalItem, visiblePages, sortType, sortParam, entityExitField);

		ProductTypeDTO proTypeDTO = productTypeService.findOneByCode(proTypeCode);
		List<ProductDTO> products = productService.findByProType(proTypeDTO, pagingComponent.getPageable());
		Result<List<ProductDTO>> result = new Result<>(//
				200, products, pagingComponent.getPaging());

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<?> search(//
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

		Result<List<ProductDTO>> result = new Result<>(//
				200, products, pagingComponent.getPaging());

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	/** Xử lý khi dữ liệu gửi lên sai hoặc thiếu **/
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<?> exceptionHandler(Exception e) {
//		Result<?> result = new Result<>(400, "Bad request: " + e.getMessage());
//		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
//	}

}
