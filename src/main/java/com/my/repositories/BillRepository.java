package com.my.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.my.entities.BillEntity;
import com.my.entities.BillStatusEnitity;

public interface BillRepository extends JpaRepository<BillEntity, Long> {

	public Long countByIsActiveTrue();

	public Long countByBilStatusAndIsActiveTrue(BillStatusEnitity entity);

	public Page<BillEntity> findByIsActiveTrue(Pageable pageable);

	public Page<BillEntity> findAllByBilStatusAndIsActiveTrue(BillStatusEnitity billStatusEnitity, Pageable pageable);

}
