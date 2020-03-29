package com.my.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.my.dto.BillDTO;
import com.my.dto.BillStatusDTO;

public interface BillService {

	public List<BillDTO> findAll(Pageable pageable);

	public BillDTO findOne(Long bilId);

	public BillDTO save(BillDTO dto);

	public Long count();

	public BillStatusDTO updateStatus(Long billId, BillDTO dto);

}
