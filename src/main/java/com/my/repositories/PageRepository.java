package com.my.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.entities.PageEntity;

public interface PageRepository extends JpaRepository<PageEntity, Long> {

	public List<PageEntity> findAllByIsActiveTrue();

	public PageEntity findByPagCodeAndIsActiveTrue(String pageCode);

}
