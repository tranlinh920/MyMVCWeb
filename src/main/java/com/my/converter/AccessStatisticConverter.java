package com.my.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.dto.AccessStatisticDTO;
import com.my.entities.AccessStatisticEntity;

@Component
public class AccessStatisticConverter {

	@Autowired
	private ModelMapper modelMapper;

	public AccessStatisticDTO toDTO(AccessStatisticEntity entity) {
		AccessStatisticDTO dto = modelMapper.map(entity, AccessStatisticDTO.class);
		return dto;
	}

	public AccessStatisticEntity toEntity(AccessStatisticDTO dto) {
		AccessStatisticEntity entity = modelMapper.map(dto, AccessStatisticEntity.class);
		return entity;
	}
}
