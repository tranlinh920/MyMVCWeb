package com.my.services;

import org.springframework.http.ResponseEntity;

import com.my.dto.ProductBillDTO;

public interface ProductBillService {

//	public ProductBillDTO findByBillId();
//
//	public ProductBillDTO findByBillId();

	public ResponseEntity<?> save(ProductBillDTO dto);
}