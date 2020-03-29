package com.my.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductBillDTO extends BaseDTO {
	private Long pbId;
	private ProductDTO pbProduct;
	private BillDTO pbBill;
	private int pbAmount;
	private int pbPrice;
}
