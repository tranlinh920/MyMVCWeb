package com.my.sevices.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.my.converter.AccessStatisticConverter;
import com.my.dto.AccessStatisticDTO;
import com.my.entities.AccessStatisticEntity;
import com.my.repositories.AccessStatisticRepository;
import com.my.services.AccessStatisticService;
import com.my.utils.DateUtil;

@Service
public class AccessStatisticServiceImpl implements AccessStatisticService {

	@Autowired
	private AccessStatisticRepository statisticRepository;

	@Autowired
	private AccessStatisticConverter statisticConverter;

	@Autowired
	private DateUtil dateUtil;

	@Override
	public Long count() {
		return statisticRepository.count();
	}

	@Override
	public Long countByCurrentDate() {
		Calendar startDate = dateUtil.getCurrentStartDay();
		Calendar endDate = dateUtil.getCurrentEndDay();
		return statisticRepository.countByAsDateBetweenAndIsActiveTrue(startDate, endDate);
	}

	@Override
	public List<AccessStatisticDTO> findAll(Pageable pageable) {
		List<AccessStatisticDTO> dtos = new ArrayList<AccessStatisticDTO>();
		List<AccessStatisticEntity> entities = statisticRepository.findAllByIsActiveTrue(pageable).getContent();
		entities.forEach(entity -> {
			dtos.add(statisticConverter.toDTO(entity));
		});
		return dtos;
	}

	@Override
	public int getTotalAccessOfCurrentDate() {
		try {
			Calendar startDay = dateUtil.getCurrentStartDay();
			Calendar endDay = dateUtil.getCurrentEndDay();
			return statisticRepository.sumAccessOfCurrentDate(startDay, endDay);
		} catch (Exception e) {
			return 0; // trường hợp không có ngày cần tìm trong db
		}
	}

	@Override
	public int getTotalAccessOfDate(int date, int month, int year) {
		try {
			Calendar startDay = dateUtil.getStartDay(date, month, year);
			Calendar endDay = dateUtil.getEndDay(date, month, year);
			return statisticRepository.sumAccessOfCurrentDate(startDay, endDay);
		} catch (Exception e) {
			return 0; // trường hợp không có ngày cần tìm trong db
		}
	}

	@Override
	public int getTotalAccessOfMonth(int month, int year) {
		try {

			Calendar firstDay = dateUtil.getFirstDayOfMonth(month, year);
			Calendar lastDate = dateUtil.getLastDayOfMonth(month, year);
			return statisticRepository.sumAccessOfMonth(firstDay, lastDate);
		} catch (Exception e) {
			return 0; // trường hợp không có ngày cần tìm trong db
		}
	}

	@Override
	public int getTotalAccessOfYear(int year) {
		try {
			Calendar firstDay = dateUtil.getFirstDayOfYear(year);
			Calendar lastDate = dateUtil.getLastDayOfYear(year);
			return statisticRepository.sumAccessOfMonth(firstDay, lastDate);
		} catch (Exception e) {
			return 0; // trường hợp không có ngày cần tìm trong db
		}
	}

	@Override
	public AccessStatisticDTO findByDate(int date, int month, int year) {
		try {
			Calendar startDay = dateUtil.getStartDay(date, month, year);
			Calendar endDay = dateUtil.getEndDay(date, month, year);
			AccessStatisticEntity entity = statisticRepository.findByDate(startDay, endDay);
			return statisticConverter.toDTO(entity);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Kiểm tra ngày trong hệ thống.Nếu chưa tồn tại thì thêm mới.Nếu tồn tại thì
	 * cập nhật lượt truy cập của nó
	 **/
	@Override
	public AccessStatisticDTO save(AccessStatisticEntity entity) {
		Calendar calendar = entity.getAsDate();
		AccessStatisticDTO dto = findByDate(calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.YEAR));
		if (dto == null) {
			entity = statisticRepository.save(entity);
			dto = statisticConverter.toDTO(entity);
		} else {
			int accessTime = dto.getAsTime();
			accessTime = accessTime + 1;
			dto.setAsTime(accessTime);
			entity = statisticRepository.save(statisticConverter.toEntity(dto));
			dto = statisticConverter.toDTO(entity);
		}
		return dto;
	}

}
