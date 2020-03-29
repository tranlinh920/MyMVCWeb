package com.my.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.dto.BillStatusDTO;
import com.my.models.Result;
import com.my.services.BillStatusService;

@RestController
@RequestMapping("/bill-statuses")
public class BillStatusController {

	@Autowired
	private BillStatusService billStatusService;

	@GetMapping()
	public ResponseEntity<?> allStatuses() {
		List<BillStatusDTO> dtos = billStatusService.findAll();
		Result<List<BillStatusDTO>> result = new Result<>(200, dtos);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
