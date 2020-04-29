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

	public Long countByBilStatus(String bilStaCode);

	public BillStatusDTO updateStatus(Long billId, BillDTO dto);

	public void delete(Long proId);

	public List<BillDTO> findByBilStatus(BillStatusDTO bilStaDto, Pageable pageable);

	public Long countByCusAndUserContaining(String searchString);

	public List<BillDTO> findByCusAndUserContaining(String searchString, Pageable pageable);

}
