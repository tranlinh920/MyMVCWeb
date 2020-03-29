package com.my.entities;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "AccessStatistics")
public class AccessStatisticEntity {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "sta_id", columnDefinition = "bigint")
//	private Long staId;

	@Id
	@Column(name = "as_date", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar asDate;

	@Column(name = "asTime", nullable = false, columnDefinition = "int")
	private int asTime;

	@Column(name = "isActive", nullable = false)
	private boolean isActive;

	public static boolean isExitField(String sortParam) {
		try {
			AccessStatisticEntity.class.getDeclaredField(sortParam);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public AccessStatisticEntity() {
	}

	public AccessStatisticEntity(Calendar asDate, int asTime) {
		this.asDate = asDate;
		this.asTime = asTime;
		this.isActive = true;
	}

}
