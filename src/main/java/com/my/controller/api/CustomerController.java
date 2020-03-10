package com.my.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.dto.CustomerDTO;
import com.my.models.Result;
import com.my.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping()
	public ResponseEntity<?> findAll() {
		List<CustomerDTO> data = customerService.findAll();
		return new ResponseEntity<>(new Result<>(200, data), HttpStatus.OK);
	}
}
