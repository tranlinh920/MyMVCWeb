package com.my.dto;

import lombok.Data;

@Data
public class ProductBillDTO {
	private Long pbId;
	private ProductDTO pbProduct;
	private BillDTO pbBill;
	private int pbPrice;
}
