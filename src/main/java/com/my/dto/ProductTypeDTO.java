package com.my.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductTypeDTO extends BaseDTO {
	private Long proTypeId;
	private String proTypeName;
	private String proTypeCode;

	public ProductTypeDTO() {

	}

	public ProductTypeDTO(Long proTypeid, String proTypeName, String proTypeCode) {
		this.proTypeId = proTypeid;
		this.proTypeName = proTypeName;
		this.proTypeCode = proTypeCode;
	}
}
