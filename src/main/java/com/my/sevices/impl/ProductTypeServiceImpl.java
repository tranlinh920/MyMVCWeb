package com.my.sevices.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.converter.ProductTypeConverter;
import com.my.dto.ProductTypeDTO;
import com.my.entities.ProductTypeEntity;
import com.my.exception.RecordNotFoundException;
import com.my.repositories.ProductTypeRepository;
import com.my.services.ProductTypeService;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

	@Autowired
	private ProductTypeRepository productTypeRepository;

	@Autowired
	private ProductTypeConverter productTypeConverter;

	public List<ProductTypeDTO> findAll() {
		List<ProductTypeDTO> dtos = new ArrayList<ProductTypeDTO>();
		List<ProductTypeEntity> entities = productTypeRepository.findByIsActiveTrue();
		entities.forEach(ele -> {
			dtos.add(productTypeConverter.toDTO(ele));
		});
		return dtos;
	}

	@Override
	public ProductTypeDTO findOneByCode(String proTypeCode) {
		ProductTypeEntity entity = productTypeRepository.findOneByProTypeCode(proTypeCode);
		if (entity == null)
			throw new RecordNotFoundException("ProductTypeEntity not found with code: " + proTypeCode);
		return productTypeConverter.toDTO(entity);
	}

}
