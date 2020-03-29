package com.my.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BillStatusDTO extends BaseDTO {
	private Long bsId;
	private String bsCode;
	private String bsName;
}
