package com.my.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.my.services.ProductService;

@Controller
public class ProductManagermentController {

	@Autowired
	ProductService productService;

	
}
