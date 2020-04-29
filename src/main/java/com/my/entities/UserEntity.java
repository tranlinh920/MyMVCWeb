package com.my.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Users")
public class UserEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", columnDefinition = "bigint")
	private Long userId;

	@Column(name = "userName", nullable = false, columnDefinition = "varchar(128)")
	private String userName;

	@Column(name = "userPassword", nullable = false, columnDefinition = "varchar(128)")
	private String userPassword;

	@Column(name = "userFullName", nullable = false, columnDefinition = "nvarchar(256)")
	private String userFullName;

	@Column(name = "userEmail", nullable = true, columnDefinition = "varchar(128)")
	private String userEmail;

	@Column(name = "userPhoneNumber", nullable = false, columnDefinition = "varchar(15)")
	private String userPhoneNumber;

	@Column(name = "userAddress", nullable = false, columnDefinition = "text")
	private String userAddress;

	@Column(name = "userLink", nullable = false, columnDefinition = "nvarchar(256)")
	private String userLink;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "userRoles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<RoleEntity> userRoles = new ArrayList<>();

	@Column(name = "userEnable", nullable = false)
	private boolean userEnable;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bilUser")
	private List<BillEntity> userBills = new ArrayList<>();

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//	private List<UserRoleEntity> userRoles = new ArrayList<>();

}
