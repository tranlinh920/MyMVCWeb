package com.my.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.entities.ProductImageEntity;

public interface ProductImageRepository extends JpaRepository<ProductImageEntity, Long> {
	ProductImageEntity findByProImageIdAndIsActive(Long imgId, boolean active);
}
