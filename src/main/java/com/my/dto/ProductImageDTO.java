package com.my.dto;

import lombok.Data;

@Data
public class ProductImageDTO {
	private Long proImageId;
	private String proImageName;

	public ProductImageDTO() {
		super();
	}

}
