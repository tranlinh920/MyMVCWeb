package com.my.controller.api;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.my.dto.AccessStatisticDTO;
import com.my.entities.AccessStatisticEntity;
import com.my.models.Result;
import com.my.paging.PagingComponent;
import com.my.services.AccessStatisticService;

@Controller
@RequestMapping("/access-statistics")
public class AccessStatisticController {

	@Autowired
	private AccessStatisticService statisticService;

	@Autowired
	private PagingComponent pagingComponent;

	@GetMapping()
	public ResponseEntity<?> all(//
			@RequestParam(defaultValue = "1") int page, //
			@RequestParam(defaultValue = "10") int limit, //
			@RequestParam(name = "sort_type", required = false) String sortType, //
			@RequestParam(name = "sort_param", required = false) String sortParam, // chọn field để sắp xếp
			@RequestParam(name = "get_by", defaultValue = "all") String getBy, //
			@RequestParam(name = "day", defaultValue = "0") int day, //
			@RequestParam(name = "month", defaultValue = "0") int month, //
			@RequestParam(name = "year", defaultValue = "0") int year//
	) {
		int totalItem = 0;
		int visiblePages = 5;
		boolean entityExitField = AccessStatisticEntity.isExitField(sortParam);
		List<AccessStatisticDTO> statistics = new ArrayList<AccessStatisticDTO>();
		AccessStatisticDTO dto;

		switch (getBy) {
		case "current":
			Calendar calendar = Calendar.getInstance();
			dto = statisticService.findByDate(calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH),
					calendar.get(Calendar.YEAR));
			if (dto != null) {
				dto.setDateAccesses(statisticService.getTotalAccessOfCurrentDate());
				dto.setWeekAccesses(0);
				dto.setMonthAccesses(statisticService.getTotalAccessOfMonth(calendar.get(Calendar.MONTH),
						calendar.get(Calendar.YEAR)));
				dto.setYearAccesses(statisticService.getTotalAccessOfYear(calendar.get(Calendar.YEAR)));
			}
			statistics.add(dto);
			break;
		case "customize":
			dto = statisticService.findByDate(day, month - 1, year);
			if (dto != null) {
				dto.setDateAccesses(statisticService.getTotalAccessOfDate(day, month - 1, year));
				dto.setWeekAccesses(0);
				dto.setMonthAccesses(statisticService.getTotalAccessOfMonth(month - 1, year));
				dto.setYearAccesses(statisticService.getTotalAccessOfYear(year));
			}
			statistics.add(dto);
			break;
		case "all-month":
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);
			int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
			List<Integer> lom = new ArrayList<Integer>();
			int numMonth = (year == currentYear) ? currentMonth : 11;
			for (int i = 0; i <= numMonth; i++) {
				lom.add(statisticService.getTotalAccessOfMonth(i, year));
			}
			statistics.add(new AccessStatisticDTO(lom));
			break;
		default:
			totalItem = statisticService.count().intValue();
			pagingComponent = pagingComponent.doPagingAndSort(//
					page, limit, totalItem, visiblePages, sortType, sortParam, entityExitField);
			statistics = statisticService.findAll(pagingComponent.getPageable());
			break;
		}

		Result<List<AccessStatisticDTO>> result = new Result<>(//
				200, statistics, pagingComponent.getPaging());

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	public static void main(String[] args) {
		Calendar endDate = Calendar.getInstance();
		endDate.set(Calendar.HOUR_OF_DAY, 0);
		System.out.println(endDate.toString());
	}

}
