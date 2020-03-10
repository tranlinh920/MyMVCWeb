package com.my.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDTO extends BaseDTO {
	private Long roleId;
	private String roleCode;
	private String roleName;
}
