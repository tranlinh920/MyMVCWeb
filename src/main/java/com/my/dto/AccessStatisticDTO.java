package com.my.dto;

import java.util.Calendar;
import java.util.List;

import lombok.Data;

@Data
public class AccessStatisticDTO {
	private Calendar asDate;
	private int asTime;
	private int dateAccesses;
	private int weekAccesses;
	private int monthAccesses;
	private int yearAccesses;
	private List<Integer> allMonthAccesses;
	private boolean isActive;

	public AccessStatisticDTO() {
	}

	public AccessStatisticDTO(List<Integer> allMonthAccesses) {
		this.allMonthAccesses = allMonthAccesses;
	}

}
