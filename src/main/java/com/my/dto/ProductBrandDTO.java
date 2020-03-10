package com.my.dto;

import lombok.Data;

@Data
public class ProductBrandDTO {
	private Long proBrandId;
	private String proBrandName;

	public ProductBrandDTO(Long proBrandId, String proBrandName) {
		super();
		this.proBrandId = proBrandId;
		this.proBrandName = proBrandName;
	}

	public ProductBrandDTO() {
		super();
	}

}
