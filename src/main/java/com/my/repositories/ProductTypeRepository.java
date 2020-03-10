package com.my.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.entities.ProductTypeEntity;

public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, Long> {

	public ProductTypeEntity findByProTypeName(String typeName);
	
	public List<ProductTypeEntity> findByIsActiveTrue();

	public ProductTypeEntity findOneByProTypeCode(String proTypeCode);
}
