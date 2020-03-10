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
		// Tránh lồng chuỗi json vì get lại bill
		entity.getBilProducts().forEach(ele -> {
			ele.setPbBill(null);
		});

		BillDTO dto = modelMapper.map(entity, BillDTO.class);
		return dto;
	}

	public BillEntity toEntity(BillDTO dto) {
		BillEntity entity = modelMapper.map(dto, BillEntity.class);
		return entity;
	}

}
