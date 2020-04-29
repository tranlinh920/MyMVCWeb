package com.my.sevices.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.my.converter.ProductBillConverter;
import com.my.dto.ProductBillDTO;
import com.my.entities.ProductBillEntity;
import com.my.models.ProductBuyingStatistic;
import com.my.models.Result;
import com.my.repositories.ProductBillRepository;
import com.my.services.ProductBillService;

@Service
public class ProductBillServiceImpl implements ProductBillService {

	@Autowired
	private ProductBillConverter productBillConverter;

	@Autowired
	private ProductBillRepository productBillRepository;

	@Override
	public ResponseEntity<?> save(ProductBillDTO dto) {
		ProductBillEntity entity = null;
		if (dto.getPbId() == null) {
			entity = productBillConverter.toEntity(dto);
			entity.setCreatedDate(Calendar.getInstance());
			entity.setActive(true);
		} else {
//			entity = productRepository.findOne(pro.getProId());
//			entity.setModifiedDate(Calendar.getInstance());
		}

		dto = productBillConverter.toDTO(productBillRepository.save(entity));

		Result<ProductBillDTO> result = new Result<ProductBillDTO>(200, dto, "");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Override
	public int countBuyingStatistics(Calendar startTime, Calendar endTime) {
		return productBillRepository.countBuyingStatistics(startTime, endTime).intValue();
	}

	@Override
	public List<ProductBuyingStatistic> findBuyingStatistics(Calendar startTime, Calendar endTime, Pageable pageable) {
		List<ProductBuyingStatistic> statistics = //
				productBillRepository.findBuyingStatistics(startTime, endTime, pageable).getContent();
		return statistics;
	}

}
