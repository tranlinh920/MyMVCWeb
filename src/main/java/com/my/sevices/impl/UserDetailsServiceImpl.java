package com.my.sevices.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.my.converter.UserConverter;
import com.my.dto.UserDTO;
import com.my.entities.UserEntity;
import com.my.repositories.UserRepository;
import com.my.services.UserService;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		UserDTO userDTO = findByUserName(userName);

		if (userDTO == null) {
			throw new UsernameNotFoundException("User " + userName + " was not found in the database");
		}

		// [ROLE_USER, ROLE_ADMIN,..]
		List<String> roleNames = getUserRoles(userDTO);

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (roleNames != null) {
			for (String role : roleNames) {
				// ROLE_USER, ROLE_ADMIN,..
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grantList.add(authority);
			}
		}

		UserDetails userDetails = (UserDetails) new User(userDTO.getUserName(), //
				userDTO.getUserPassword(), grantList);

		return userDetails;
	}

	@Override
	public UserDTO findByIdAndGetRoles(Long userId) {
		return userConverter.toDTO(userRepository.findOne(userId));
	}

	@Override
	public List<UserDTO> findAllGetRoles() {
		List<UserDTO> dtos = new ArrayList<>();
		List<UserEntity> entities = userRepository.findAll();
		entities.forEach(entity -> {
			dtos.add(userConverter.toDTO(entity));
		});
		return dtos;
	}

	@Override
	public UserDTO findByUserName(String userName) {
		UserEntity entity = userRepository.findByUserName(userName);
		return userConverter.toDTO(entity);
	}

	@Override
	public UserDTO save(UserDTO userdto) {
		UserEntity userEntity;
		if (userdto.getUserId() == null) {
			userEntity = userConverter.toEntity(userdto);
			userEntity.setCreatedDate(Calendar.getInstance());
			userEntity.setActive(true);
		} else {
			userEntity = userRepository.findOne(userdto.getUserId());
			userEntity.setUserName(userdto.getUserName());
			userEntity.setUserPassword(userdto.getUserPassword());
			userEntity.setUserFullName(userdto.getUserFullName());
			userEntity.setUserEmail(userdto.getUserEmail());
			userEntity.setUserPhoneNumber(userdto.getUserPhoneNumber());
			userEntity.setUserAddress(userdto.getUserAddress());
			userEntity.setModifiedDate(Calendar.getInstance());
		}

		userEntity = userRepository.save(userEntity);

		if (userEntity == null)
			throw new RuntimeException("Can not save user");
		return userConverter.toDTO(userEntity);
	}

	private List<String> getUserRoles(UserDTO userDTO) {
		List<String> roleNames = new ArrayList<String>();
		userDTO.getUserRoles().forEach(role -> {
			roleNames.add(role.getRoleName());
		});
		return roleNames;
	}

}