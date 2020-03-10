package com.my.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.my.entities.BillEntity;

public interface BillRepository extends JpaRepository<BillEntity, Long> {

	public Long countByIsActiveTrue();

	public Page<BillEntity> findByIsActiveTrue(Pageable pageable);

}
