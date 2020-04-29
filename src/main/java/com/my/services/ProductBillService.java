package com.my.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.my.dto.ProductBillDTO;
import com.my.models.ProductBuyingStatistic;

public interface ProductBillService {

//	public ProductBillDTO findByBillId();
//
//	public ProductBillDTO findByBillId();

	public ResponseEntity<?> save(ProductBillDTO dto);

	public int countBuyingStatistics(Calendar startTime, Calendar endTime);

	public List<ProductBuyingStatistic> findBuyingStatistics(Calendar startTime, Calendar endTime, Pageable pageable);
}