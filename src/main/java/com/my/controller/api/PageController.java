package com.my.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.dto.PageDTO;
import com.my.entities.PageEntity;
import com.my.models.Result;
import com.my.services.PageService;

@RestController
@RequestMapping("/pages")
public class PageController {

	@Autowired
	private PageService pageService;

	@GetMapping()
	public ResponseEntity<?> all() {
		List<PageDTO> dtos = pageService.findAll();
		Result<List<PageDTO>> result = new Result<>(200, dtos);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/{pagId}")
	public ResponseEntity<?> oneById(@PathVariable("pagId") Long pagId) {
		PageDTO dto = pageService.findOne(pagId);
		Result<PageDTO> result = new Result<>(200, dto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/by-page-code/{pageCode}")
	public ResponseEntity<?> oneByCode(@PathVariable("pageCode") String pageCode) {
		PageDTO dto = pageService.findByCode(pageCode);
		Result<PageDTO> result = new Result<>(200, dto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<?> newPage(@RequestBody PageEntity entity) {
		PageDTO dto = pageService.save(entity);
		Result<PageDTO> result = new Result<PageDTO>(200, dto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> replaceProduct(@PathVariable(name = "id") Long proId, @RequestBody PageEntity entity) {
		entity.setPagId(proId);
		PageDTO dto = pageService.save(entity);
		Result<PageDTO> result = new Result<PageDTO>(200, dto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") Long proId) {
		pageService.delete(proId);
		Result<?> result = new Result<>(200, "Delete successful");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
