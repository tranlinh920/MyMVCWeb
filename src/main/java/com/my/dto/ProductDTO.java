package com.my.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductDTO extends BaseDTO {
	private Long proId;
	private String proName;
	private ProductTypeDTO proType;
	private ProductBrandDTO proBrand;
	private ProductOriginDTO proOrigin;
	private int proPrice;
	private boolean proIsDiscount;
	private double proDiscountRatio;
	private int proAmount;
	private boolean proIsScarcity;
	private String proDescribe;
	private List<ProductImageDTO> proImages;
}
