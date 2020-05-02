package com.my.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PageDTO extends BaseDTO {
	private Long pagId;
	private String pagName;
	private String pagCode;
	private String pagContent;
}
