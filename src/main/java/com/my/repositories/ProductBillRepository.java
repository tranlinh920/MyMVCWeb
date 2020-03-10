package com.my.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.entities.ProductBillEntity;

public interface ProductBillRepository extends JpaRepository<ProductBillEntity, Long> {

}
