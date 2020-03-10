package com.my.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-bills")
public class ProductBillController {

//	@Autowired
//	private ProductBillService productBillService;

	@GetMapping("/bill/{id}")
	public ResponseEntity<?> getByBillId() {
		return null;
	}
}
