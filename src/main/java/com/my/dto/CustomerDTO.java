package com.my.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerDTO extends BaseDTO {
	private Long id;
	private String cusFullName;
	private String cusEmail;
	private String cusPhoneNumber;
	private String cusAddress;
//	private List<BillDTO> cusBills;
}
