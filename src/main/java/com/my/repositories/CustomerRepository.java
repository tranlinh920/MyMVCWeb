package com.my.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.entities.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

}
