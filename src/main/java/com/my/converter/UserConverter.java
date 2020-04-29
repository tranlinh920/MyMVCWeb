package com.my.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.dto.RoleDTO;
import com.my.dto.UserDTO;
import com.my.entities.RoleEntity;
import com.my.entities.UserEntity;
import com.my.utils.EncrytedPasswordUtils;
import com.restfb.types.User;

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

	public RoleDTO toRoleDTO(RoleEntity entity) {
		RoleDTO dto = modelMapper.map(entity, RoleDTO.class);
		return dto;
	}

	public UserDTO toDTO(User user) {

		List<RoleDTO> roles = new ArrayList<RoleDTO>();
		roles.add(new RoleDTO(new Long(2), "role-user", "ROLE_USER"));

		UserDTO dto = new UserDTO();
		dto.setUserName(user.getId());
		dto.setUserPassword(
				EncrytedPasswordUtils.encrytePassword(String.valueOf(Math.round(Math.random() * 100000000))));
		dto.setUserFullName(user.getName());
		dto.setUserEmail(user.getEmail() != null ? user.getEmail() : "");
		dto.setUserPhoneNumber("");
		dto.setUserAddress("");
		dto.setUserLink(user.getLink());
		dto.setUserRoles(roles);
		dto.setActive(true);
		dto.setUserEnable(true);
		return dto;
	}

}
