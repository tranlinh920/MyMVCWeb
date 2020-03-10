package com.my.dto;

import lombok.Data;

@Data
public class ProductOriginDTO {
	private Long proOriginId;
	private String proOriginName;

	public ProductOriginDTO() {
		super();
	}

}
