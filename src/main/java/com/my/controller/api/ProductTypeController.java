package com.my.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.dto.ProductTypeDTO;
import com.my.models.Result;
import com.my.sevices.impl.ProductTypeServiceImpl;

@RestController
@RequestMapping("/product-types")
public class ProductTypeController {

	@Autowired
	ProductTypeServiceImpl productTypeService;

	@GetMapping()
	public Result<List<ProductTypeDTO>> all() {
		List<ProductTypeDTO> data = productTypeService.findAll();
		return new Result<List<ProductTypeDTO>>(200, data);
	}
}
