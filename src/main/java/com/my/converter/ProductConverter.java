package com.my.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.dto.ProductDTO;
import com.my.entities.ProductEntity;
import com.my.models.ProductUpload;

@Component
public class ProductConverter {

	@Autowired
	private ModelMapper modelMapper;

	public ProductDTO toDTO(ProductEntity entity) {
		ProductDTO dto = modelMapper.map(entity, ProductDTO.class);
		return dto;
	}

	public ProductEntity toEntity(ProductDTO dto) {
		ProductEntity entity = modelMapper.map(dto, ProductEntity.class);
		return entity;
	}

	public ProductEntity toEntity(ProductUpload pro) {
		ProductEntity entity = modelMapper.map(pro, ProductEntity.class);
		return entity;
	}
}
