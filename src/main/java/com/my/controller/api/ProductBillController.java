package com.my.controller.api;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.my.contant.DateContant;
import com.my.models.ProductBuyingStatistic;
import com.my.models.Result;
import com.my.paging.PagingComponent;
import com.my.services.ProductBillService;
import com.my.utils.DateUtil;

@RestController
@RequestMapping("/product-bills")
public class ProductBillController {

	@Autowired
	private ProductBillService productBillService;

	@Autowired
	private PagingComponent pagingComponent;

	@Autowired
	private DateUtil dateUtil;

	@GetMapping("/buying-statistics")
	public ResponseEntity<?> statistic(//
			@RequestParam(defaultValue = "1") int page, //
			@RequestParam(defaultValue = "10") int limit, //
			@RequestParam(defaultValue = "5") int visiblePages, //
			@RequestParam(name = "sort_type", required = false) String sortType, //
			@RequestParam(name = "sort_param", required = false) String sortParam, //
			@RequestParam(name = "day", defaultValue = "0") int day, //
			@RequestParam(name = "month", defaultValue = "0") int month, //
			@RequestParam(name = "year", defaultValue = "0") int year//
	) {
		List<ProductBuyingStatistic> buyStatistics;
		int totalItem = 0;
		Calendar startTime;
		Calendar endTime;

		if (day == DateContant.ALL_DAY) {
			startTime = dateUtil.getFirstDayOfMonth(month - 1, year);
			endTime = dateUtil.getLastDayOfMonth(month - 1, year);
			
			if (month == DateContant.ALL_MONTH) {
				startTime = dateUtil.getFirstDayOfYear(year);
				endTime = dateUtil.getLastDayOfYear(year);
			}
			
		} else {
			startTime = dateUtil.getStartDay(day, month - 1, year);
			endTime = dateUtil.getEndDay(day, month - 1, year);
		}

		totalItem = productBillService.countBuyingStatistics(startTime, endTime);

		boolean entityExitField = ProductBuyingStatistic.isExitField(sortParam);
		pagingComponent = pagingComponent.doPagingAndSort(//
				page, limit, totalItem, visiblePages, sortType, sortParam, entityExitField);

		buyStatistics = productBillService.findBuyingStatistics(startTime, endTime, pagingComponent.getPageable());

		Result<List<ProductBuyingStatistic>> result = new Result<>(//
				200, buyStatistics, pagingComponent.getPaging());

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
