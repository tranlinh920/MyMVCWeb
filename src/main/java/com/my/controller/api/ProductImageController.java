package com.my.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.my.dto.ProductImageDTO;
import com.my.models.Result;
import com.my.sevices.impl.ProductImageServiceImpl;

@Controller
@RequestMapping("/product-images")
public class ProductImageController {

	@Autowired
	ProductImageServiceImpl productImageService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> one(@PathVariable(name = "id") Long proId) {
		ProductImageDTO images = productImageService.findOne(proId);
		Result<ProductImageDTO> result = new Result<>(200, images);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
