package com.my.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.my.bo.BillBo;
import com.my.dto.BillDTO;
import com.my.dto.BillStatusDTO;
import com.my.entities.BillEntity;
import com.my.models.Result;
import com.my.paging.PagingComponent;
import com.my.services.BillService;

@RestController
@RequestMapping("/bills")
public class BillController {

	@Autowired
	private BillService billService;

	@Autowired
	private BillBo billbo;

	@Autowired
	private PagingComponent pagingComponent;

	@GetMapping()
	public ResponseEntity<?> all(//
			@RequestParam(defaultValue = "1") int page, //
			@RequestParam(defaultValue = "10") int limit, //
			@RequestParam(name = "sort_type", required = false) String sortType, //
			@RequestParam(name = "sort_param", required = false) String sortParam // chọn field để sắp xếp
	) {

		int totalItem = billService.count().intValue();
		int visiblePages = 5;
		boolean entityExitField = BillEntity.isExitField(sortParam);
		pagingComponent = pagingComponent.doPagingAndSort(//
				page, limit, totalItem, visiblePages, sortType, sortParam, entityExitField);

		List<BillDTO> dtos = billService.findAll(pagingComponent.getPageable());
		Result<List<BillDTO>> result = new Result<>(200, dtos, pagingComponent.getPaging());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> one(@PathVariable("id") Long bilId) {
		BillDTO dto = billService.findOne(bilId);
		Result<BillDTO> result = new Result<>(200, dto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<?> save(@RequestBody BillDTO dto) {
		billbo.checkBill(dto);
		BillDTO billDto = billService.save(dto);
		Result<BillDTO> result = new Result<>(200, billDto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping("/status/{id}")
	public ResponseEntity<?> updateStatus(@PathVariable("id") Long billId, @RequestBody BillDTO dto) {
		BillStatusDTO billStatusDto = billService.updateStatus(billId, dto);
		Result<BillStatusDTO> result = new Result<>(200, billStatusDto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
