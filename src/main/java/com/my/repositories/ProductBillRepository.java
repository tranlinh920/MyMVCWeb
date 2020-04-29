package com.my.repositories;

import java.util.Calendar;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.my.entities.ProductBillEntity;
import com.my.models.ProductBuyingStatistic;

public interface ProductBillRepository extends JpaRepository<ProductBillEntity, Long> {

	@Query("select count(distinct p.proId) "//
			+ "from ProductBillEntity pb "//
			+ "join pb.pbProduct p " //
			+ "where pb.createdDate between :startTime and :endTime "//
	)
	public Long countBuyingStatistics(@Param("startTime") Calendar startTime, @Param("endTime") Calendar endTime);

	@Query("select new com.my.models.ProductBuyingStatistic(p.proId, p.proName, count(p.proId), sum(pb.pbAmount)) "//
			+ "from ProductBillEntity pb "//
			+ "join pb.pbProduct p " //
			+ "where pb.createdDate between :startTime and :endTime "//
			+ "group by p.proId "//
			+ "order by count(p.proId) desc"//
	)
	public Page<ProductBuyingStatistic> findBuyingStatistics(@Param("startTime") Calendar startTime,
			@Param("endTime") Calendar endTime, Pageable pageable);
}
