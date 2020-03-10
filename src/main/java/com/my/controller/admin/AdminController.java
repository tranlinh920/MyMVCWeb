package com.my.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

	@GetMapping("/admin")
	public String getHomePage() {
		return "redirect:admin/thong-ke";
	}

	@GetMapping("/admin/dang-nhap")
	public String getLoginPage() {
		return "admin/login";
	}

	@GetMapping("/admin/thong-ke")
	public ModelAndView getDashboardPage() {
		ModelAndView mav = new ModelAndView("admin/dashboard/dashboard");
		mav.addObject("", "");
		return mav;
	}

	@GetMapping("/admin/quan-ly-san-pham")
	public ModelAndView getProductMangagermentPage() {
		ModelAndView mav = new ModelAndView("admin/product-managerment/product");
		return mav;
	}
}