package com.my.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.my.entities.BillEntity;
import com.my.entities.BillStatusEnitity;

public interface BillRepository extends JpaRepository<BillEntity, Long> {

	public Long countByIsActiveTrue();

	public Long countByBilStatusAndIsActiveTrue(BillStatusEnitity entity);

	public Page<BillEntity> findByIsActiveTrue(Pageable pageable);

	public Page<BillEntity> findAllByBilStatusAndIsActiveTrue(BillStatusEnitity billStatusEnitity, Pageable pageable);

	@Query("select count(b) "//
			+ "from BillEntity b "//
			+ "left join b.bilCus bc "//
			+ "left join b.bilUser bu "//
			+ "where bc.cusFullName like concat('%',:searchString,'%') "//
			+ "or bc.cusPhoneNumber like concat('%',:searchString,'%') "//
			+ "or bu.userFullName like concat('%',:searchString,'%') "//
			+ "or bu.userPhoneNumber like concat('%',:searchString,'%') "//
			+ "and b.isActive = :isActive ")
	public Long countByCusAndUserContainingAndIsActive(@Param("searchString") String searchString,
			@Param("isActive") boolean isActive);

	@Query("select b " //
			+ "from BillEntity b "//
			+ "left join b.bilCus bc "//
			+ "left join b.bilUser bu "//
			+ "where bc.cusFullName like concat('%',:searchString,'%') "//
			+ "or bc.cusPhoneNumber like concat('%',:searchString,'%') "//
			+ "or bu.userFullName like concat('%',:searchString,'%') "//
			+ "or bu.userPhoneNumber like concat('%',:searchString,'%') "//
			+ "and b.isActive = :isActive ")
	public Page<BillEntity> findByCusAndUserContainingAndIsActive(@Param("searchString") String searchString,
			@Param("isActive") boolean isActive, Pageable pageable);

}
