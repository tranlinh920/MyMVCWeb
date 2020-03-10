package com.my.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.dto.ProductBillDTO;
import com.my.entities.ProductBillEntity;

@Component
public class ProductBillConverter {

	@Autowired
	private ModelMapper modelMapper;

	public ProductBillDTO toDTO(ProductBillEntity entity) {
		ProductBillDTO dto = modelMapper.map(entity, ProductBillDTO.class);
		return dto;
	}

	public ProductBillEntity toEntity(ProductBillDTO dto) {
		ProductBillEntity entity = modelMapper.map(dto, ProductBillEntity.class);
		return entity;
	}

}
