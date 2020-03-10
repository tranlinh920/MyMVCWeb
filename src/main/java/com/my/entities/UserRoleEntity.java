package com.my.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
//@Table(name = "UserRoles")
public class UserRoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ur_id", columnDefinition = "bigint")
	Long urId;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "user_id", nullable = false, columnDefinition = "bigint")
//	private UserEntity user;
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "role_id", nullable = false, columnDefinition = "bigint")
//	private RoleEntity role;
}
