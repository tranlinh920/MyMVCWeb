package com.my.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BillDTO extends BaseDTO {
	private Long bilId;
	private int bilAmount;
	private UserDTO bilUser;
	private CustomerDTO bilCus;
	private List<ProductBillDTO> bilProducts;
	private BillStatusDTO bilStatus;
}
