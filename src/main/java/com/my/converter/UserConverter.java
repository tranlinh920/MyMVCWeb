package com.my.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.dto.UserDTO;
import com.my.entities.UserEntity;

@Component
public class UserConverter {
	@Autowired
	private ModelMapper modelMapper;

	public UserDTO toDTO(UserEntity entity) {
		UserDTO dto = modelMapper.map(entity, UserDTO.class);
		return dto;
	}

	public UserEntity toEntity(UserDTO dto) {
		UserEntity entity = modelMapper.map(dto, UserEntity.class);
		return entity;
	}
}
