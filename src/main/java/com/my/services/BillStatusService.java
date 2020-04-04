package com.my.services;

import java.util.List;

import com.my.dto.BillStatusDTO;

public interface BillStatusService {

	public BillStatusDTO findByBsCode(String bsCode);

	public List<BillStatusDTO> findAll();


}
