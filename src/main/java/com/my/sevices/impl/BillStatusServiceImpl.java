package com.my.sevices.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.converter.BillStatusConverter;
import com.my.dto.BillStatusDTO;
import com.my.entities.BillStatusEnitity;
import com.my.repositories.BillStatusRepository;
import com.my.services.BillStatusService;

@Service
@Transactional
public class BillStatusServiceImpl implements BillStatusService {

	@Autowired
	private BillStatusRepository billStatusRepository;

	@Autowired
	private BillStatusConverter billStatusConverter;

	@Override
	public BillStatusDTO findByBsCode(String bsCode) {
		return billStatusConverter.toDTO(billStatusRepository.findByBsCode(bsCode));
	}

	@Override
	public List<BillStatusDTO> findAll() {
		List<BillStatusDTO> dtos = new ArrayList<BillStatusDTO>();
		List<BillStatusEnitity> entities = billStatusRepository.findAll();
		entities.forEach(entity -> {
			dtos.add(billStatusConverter.toDTO(entity));
		});
		return dtos;
	}

}
