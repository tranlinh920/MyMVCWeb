package com.my.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.entities.RoleEntity;

@Transactional
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	public List<String> findByRoleId(Long id);
	
//	@Query(value = "SELECT R.* FROM ROLES R", nativeQuery = true)
//	public List<RoleEntity> getRoles();
//
//	@Query(value = "SELECT R.* FROM USERS U " + //
//			"JOIN USERROLES UR ON U.USER_ID = UR.USER_ID " + //
//			"JOIN ROLES R ON UR.ROLE_ID = R.ROLE_ID " + //
//			"WHERE U.USER_ID = :userId", nativeQuery = true)
//	public List<RoleEntity> getRolesByUserId(@Param("userId") Long userId);

}
