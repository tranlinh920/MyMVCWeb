package com.my.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.my.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	public UserEntity findByUserName(String userName);
	
	@Query("select u from UserEntity u join fetch u.userRoles")
	public List<UserEntity> findAllAndGetRoles();

}
