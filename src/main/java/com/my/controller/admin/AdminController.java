package com.my.controller.admin;

import java.util.Calendar;

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

	@GetMapping("/admin/thong-ke-luot-truy-cap")
	public ModelAndView getAccessStatisticsPage() {
		ModelAndView mav = new ModelAndView("admin/dashboard/access-statistic");
		Calendar calendar = Calendar.getInstance();
		mav.addObject("currentDate", calendar.get(Calendar.DATE));
		mav.addObject("currentMonth", calendar.get(Calendar.MONTH));
		mav.addObject("currentYear", calendar.get(Calendar.YEAR));
		return mav;
	}

	@GetMapping("/admin/thong-ke-san-pham")
	public ModelAndView getProductStatisticsPage() {
		ModelAndView mav = new ModelAndView("admin/dashboard/product-statistic");
		Calendar calendar = Calendar.getInstance();
		mav.addObject("currentDate", calendar.get(Calendar.DATE));
		mav.addObject("currentMonth", calendar.get(Calendar.MONTH));
		mav.addObject("currentYear", calendar.get(Calendar.YEAR));
		return mav;
	}

	@GetMapping("/admin/quan-ly-san-pham")
	public ModelAndView getProductMangagermentPage() {
		ModelAndView mav = new ModelAndView("admin/product-managerment/product");
		return mav;
	}

	@GetMapping("/admin/quan-ly-hoa-don")
	public ModelAndView getBillMangagermentPage() {
		ModelAndView mav = new ModelAndView("admin/bill-managerment/bill");
		return mav;
	}
}