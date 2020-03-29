package com.my.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.dto.BillDTO;
import com.my.entities.BillEntity;

@Component
public class BillConverter {

	@Autowired
	private ModelMapper modelMapper;

	public BillDTO toDTO(BillEntity entity) {
		BillDTO dto = modelMapper.map(entity, BillDTO.class);

		// Tránh lồng chuỗi json vì get lại bill
		dto.getBilProducts().forEach(ele -> {
			ele.setPbBill(null);
		});

		return dto;
	}

	public BillEntity toEntity(BillDTO dto) {
		BillEntity entity = modelMapper.map(dto, BillEntity.class);
		return entity;
	}

}
