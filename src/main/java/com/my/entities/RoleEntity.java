package com.my.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Roles")
public class RoleEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id", columnDefinition = "bigint")
	private Long roleId;

	@Column(name = "roleCode", nullable = false, columnDefinition = "varchar(128)")
	private String roleCode;

	@Column(name = "roleName", nullable = false, columnDefinition = "nvarchar(256)")
	private String roleName;

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
//	private List<UserRoleEntity> roles = new ArrayList<>();

	@ManyToMany(mappedBy = "userRoles", fetch = FetchType.LAZY)
	private List<UserEntity> users = new ArrayList<>();
}
