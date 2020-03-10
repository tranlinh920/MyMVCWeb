package com.my.interceptors;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.my.dto.UserDTO;
import com.my.models.Category;
import com.my.services.UserService;
import com.my.sevices.impl.MenuServiceImpl;
import com.my.utils.SecurityUtil;

public class WebInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private MenuServiceImpl menuService;

	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// Khởi tạo navigation menu
		if (request.getAttribute("navs") == null) {
			List<Category> navs = menuService.getNavigationBars();
			request.setAttribute("navs", navs);
		}

		// Khởi tạo side bar
		if (request.getAttribute("sideBars") == null) {
			List<Category> sideBars = menuService.getSideBars();
			request.setAttribute("sideBars", sideBars);
		}

		// Khởi tạo thông tin user
		if (SecurityUtil.getUserDetails() != null) {
			if (request.getSession().getAttribute("user") == null) {
				UserDTO userDto = userService.findByUserName(SecurityUtil.getUserDetails().getUsername());
				request.getSession().setAttribute("user", userDto);
			}
		}

		// số lượng sản phẩm trong giỏ
		if (request.getSession().getAttribute("CART-SIZE") != null) {
			int cartSize = (int) request.getSession().getAttribute("CART-SIZE");
			request.setAttribute("cartSize", cartSize);
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
