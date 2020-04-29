package com.my.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends BaseDTO {
	private Long userId;
	private String userName;
	private String userPassword;
	private String userFullName;
	private String userEmail;
	private String userPhoneNumber;
	private String userAddress;
	private String userLink;
	private List<RoleDTO> userRoles;
	private boolean userEnable;
}
