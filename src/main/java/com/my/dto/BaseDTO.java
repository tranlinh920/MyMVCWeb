package com.my.dto;

import java.util.Calendar;

import lombok.Data;

@Data
public abstract class BaseDTO {

	private Calendar createdDate;
	private Calendar modifiedDate;
	private String createdBy;
	private String modifiedBy;
	private boolean isActive;
}
