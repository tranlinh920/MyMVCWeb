package com.my.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.my.dto.BillDTO;

public interface BillService {

	public List<BillDTO> findAll(Pageable pageable);

	public BillDTO save(BillDTO dto);

	public Long count();

}
