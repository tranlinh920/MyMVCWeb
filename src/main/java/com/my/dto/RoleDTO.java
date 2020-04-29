package com.my.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDTO extends BaseDTO {
	private Long roleId;
	private String roleCode;
	private String roleName;

	public RoleDTO() {
		// TODO Auto-generated constructor stub
	}

	public RoleDTO(Long roleId, String roleCode, String roleName) {
		this.roleId = roleId;
		this.roleCode = roleCode;
		this.roleName = roleName;
	}

}
