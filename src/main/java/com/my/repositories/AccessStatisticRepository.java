package com.my.repositories;

import java.util.Calendar;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.my.entities.AccessStatisticEntity;

public interface AccessStatisticRepository extends JpaRepository<AccessStatisticEntity, Long> {

	public Page<AccessStatisticEntity> findAllByIsActiveTrue(Pageable pageable);

	public Long countByAsDateBetweenAndIsActiveTrue(Calendar startDate, Calendar endDate);

	@Query(value = "SELECT sum(asTime) FROM AccessStatisticEntity WHERE asDate BETWEEN :firstDate AND :lastDate")
	public int sumAccessOfMonth(@Param("firstDate") Calendar firstDate, @Param("lastDate") Calendar lastDate);

	@Query(value = "SELECT sum(asTime) FROM AccessStatisticEntity WHERE asDate BETWEEN :startDate AND :endDate")
	public int sumAccessOfCurrentDate(@Param("startDate") Calendar startDate, @Param("endDate") Calendar endDate);

	@Query(value = "FROM AccessStatisticEntity WHERE asDate BETWEEN :startDate AND :endDate")
	public AccessStatisticEntity findByDate(@Param("startDate") Calendar startDay, @Param("endDate") Calendar endDay);
}
