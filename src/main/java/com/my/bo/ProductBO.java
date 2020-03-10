package com.my.bo;

import org.springframework.stereotype.Component;

import com.my.dto.ProductDTO;

@Component
public class ProductBO {
	
	public boolean authentic( ProductDTO dto) {
//		if(dto.getProName() == null || Double.isNaN(dto.getProPrice()) || dto.getProTypeName() == null)
//			return false;
		return true;
	}
	

}
