package com.my.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.dto.CustomerDTO;
import com.my.entities.CustomerEntity;

@Component
public class CustomerConverter {

	@Autowired
	private ModelMapper modelMapper;

	public CustomerDTO toDTO(CustomerEntity entity) {
		CustomerDTO dto = modelMapper.map(entity, CustomerDTO.class);
		return dto;
	}

	public CustomerEntity toEntity(CustomerDTO dto) {
		CustomerEntity entity = modelMapper.map(dto, CustomerEntity.class);
		return entity;
	}

}
