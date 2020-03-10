package com.my.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.my.dto.UserDTO;
import com.my.models.Result;
import com.my.services.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/user/{id}")
	public ResponseEntity<?> aaa(@PathVariable("id") Long userId) {
		UserDTO data = userService.findByIdAndGetRoles(userId);
		return new ResponseEntity<>(new Result<>(200, data), HttpStatus.OK);
	}

	@GetMapping("/user")
	public ResponseEntity<?> bbb() {
		List<UserDTO> data = userService.findAllGetRoles();
		return new ResponseEntity<>(new Result<>(200, data), HttpStatus.OK);
	}
	

}
