package com.my.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.dto.BillStatusDTO;
import com.my.entities.BillStatusEnitity;

@Component
public class BillStatusConverter {

	@Autowired
	private ModelMapper modelMapper;

	public BillStatusDTO toDTO(BillStatusEnitity entity) {
		BillStatusDTO dto = modelMapper.map(entity, BillStatusDTO.class);
		return dto;
	}

	public BillStatusEnitity toEntity(BillStatusDTO dto) {
		BillStatusEnitity entity = modelMapper.map(dto, BillStatusEnitity.class);
		return entity;
	}
}
