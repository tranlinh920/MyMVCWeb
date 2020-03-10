package com.my.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.my.dto.UserDTO;

public interface UserService {
	public UserDetails loadUserByUsername(String userName);

	public UserDTO findByIdAndGetRoles(Long userId);

	public List<UserDTO> findAllGetRoles();

	public UserDTO findByUserName(String userName);

	public UserDTO save(UserDTO userdto);

}
