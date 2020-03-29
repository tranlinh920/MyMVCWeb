package com.my.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.entities.BillStatusEnitity;

public interface BillStatusRepository extends JpaRepository<BillStatusEnitity, Long> {
	public BillStatusEnitity findByBsCode(String bsCode);
}
