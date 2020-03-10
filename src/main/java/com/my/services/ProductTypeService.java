package com.my.services;

import java.util.List;

import com.my.dto.ProductTypeDTO;

public interface ProductTypeService {
	public List<ProductTypeDTO> findAll();
	ProductTypeDTO findOneByCode(String proTypeCode);
}
