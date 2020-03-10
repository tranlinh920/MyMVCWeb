package com.my.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.dto.ProductTypeDTO;
import com.my.entities.ProductTypeEntity;

@Component
public class ProductTypeConverter {

	@Autowired
	private ModelMapper modelMapper;

	public ProductTypeDTO toDTO(ProductTypeEntity entity) {
		ProductTypeDTO dto = modelMapper.map(entity, ProductTypeDTO.class);
		return dto;
	}

	public ProductTypeEntity toEntity(ProductTypeDTO typeDTO) {
		return modelMapper.map(typeDTO, ProductTypeEntity.class);
	}

}
