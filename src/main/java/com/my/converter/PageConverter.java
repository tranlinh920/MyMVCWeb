package com.my.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.dto.PageDTO;
import com.my.entities.PageEntity;

@Component
public class PageConverter {

	@Autowired
	private ModelMapper modelMapper;

	public PageDTO toDTO(PageEntity entity) {
		PageDTO dto = modelMapper.map(entity, PageDTO.class);
		return dto;
	}

	public PageEntity toEntity(PageDTO dto) {
		PageEntity entity = modelMapper.map(dto, PageEntity.class);
		return entity;
	}

}
