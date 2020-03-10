package com.my.entities;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseEntity {

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar modifiedDate;

	@Column(name = "createdBy", columnDefinition = "varchar(128)")
	private String createdBy;
	
	@Column(name = "modifiedBy", columnDefinition = "varchar(128)")
	private String modifiedBy;

	@Column(name = "isActive", nullable = false)
	private boolean isActive;

	public static boolean isExitField(String sortParam) {
		try {
			BaseEntity.class.getDeclaredField(sortParam);
			return true;
		} catch (Exception e) {
			System.out.println("da vao exception");
			return false;
		}
	}
}
