package com.my.bo;

import org.springframework.stereotype.Component;

import com.my.dto.BillDTO;
import com.my.dto.CustomerDTO;

@Component
public class BillBo {

	public void checkBill(BillDTO billDto) {
		if (billDto.getBilProducts() == null || billDto.getBilProducts().size() == 0)
			throw new RuntimeException("Vui lòng chọn sản phẩm vào giỏ !");
		if (!isCustomerVaild(billDto.getBilCus()))
			throw new RuntimeException("Vui lòng nhập thông tin thanh toán");
	}

	private boolean isCustomerVaild(CustomerDTO cusDto) {
		if (cusDto.getCusFullName() == null || cusDto.getCusFullName().equals(""))
			return false;
		if (cusDto.getCusPhoneNumber() == null)
			return false;
		if (cusDto.getCusAddress() == null || cusDto.getCusFullName().equals(""))
			return false;
		return true;
	}
}
