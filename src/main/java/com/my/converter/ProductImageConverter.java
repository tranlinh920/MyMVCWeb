package com.my.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.dto.ProductImageDTO;
import com.my.entities.ProductImageEntity;

@Component
public class ProductImageConverter {
	
	@Autowired
	private ModelMapper modelMapper;

	public ProductImageDTO toDto(ProductImageEntity entity) {
		return modelMapper.map(entity, ProductImageDTO.class);
	}
}
