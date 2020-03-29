package com.my.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.my.dto.AccessStatisticDTO;
import com.my.entities.AccessStatisticEntity;

public interface AccessStatisticService {

	public Long count();

	public Long countByCurrentDate();

	public int getTotalAccessOfCurrentDate();

	public int getTotalAccessOfDate(int date, int month, int year);

	public int getTotalAccessOfMonth(int month, int year);

	public int getTotalAccessOfYear(int year);

	public List<AccessStatisticDTO> findAll(Pageable pageable);

	public AccessStatisticDTO findByDate(int date, int month, int year);

	public AccessStatisticDTO save(AccessStatisticEntity entity);

}
