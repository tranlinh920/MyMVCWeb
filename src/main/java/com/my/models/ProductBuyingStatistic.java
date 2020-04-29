package com.my.models;

import lombok.Data;

@Data
public class ProductBuyingStatistic {
	private Long proId;
	private String proName;
	private Long buyTimes;
	private Long buyAmount;

	public ProductBuyingStatistic() {
	}

	public ProductBuyingStatistic(Long proId, String proName, Long buyTimes, Long buyAmount) {
		this.proId = proId;
		this.proName = proName;
		this.buyTimes = buyTimes;
		this.buyAmount = buyAmount;
	}

	public static boolean isExitField(String sortParam) {
		try {
			ProductBuyingStatistic.class.getDeclaredField(sortParam);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
