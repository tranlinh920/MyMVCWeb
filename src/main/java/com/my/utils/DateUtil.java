package com.my.utils;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.my.entities.AccessStatisticEntity;
import com.my.services.AccessStatisticService;

@Component
public class DateUtil {

	@Autowired
	private AccessStatisticService accessStatisticService;

	public Calendar getCurrentStartDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}

	public Calendar getCurrentEndDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar;
	}

	public Calendar getStartDay(int date, int month, int year) {
		Calendar calendar = getCurrentStartDay();
		calendar.set(Calendar.DAY_OF_MONTH, date);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		return calendar;
	}

	public Calendar getEndDay(int date, int month, int year) {
		Calendar calendar = getCurrentEndDay();
		calendar.set(Calendar.DAY_OF_MONTH, date);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		return calendar;
	}

	public Calendar getFirstDayOfMonth(int month, int year) {
		Calendar calendar = getCurrentStartDay();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		return calendar;
	}

	public Calendar getLastDayOfMonth(int month, int year) {
		Calendar calendar = getCurrentEndDay();
		calendar.set(Calendar.DAY_OF_MONTH, getMonthDays(month + 1, year));
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		return calendar;
	}

	public Calendar getStartDayOfMonth(int day, int month) {
		Calendar calendar = getCurrentStartDay();
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.MONTH, month);
		return calendar;
	}

	public Calendar getEndDayOfMonth(int day, int month) {
		Calendar calendar = getCurrentEndDay();
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.MONTH, month);
		return calendar;
	}

	public Calendar getDayInFirstWeekOfMonth(int month) {
		Calendar calendar = getCurrentStartDay();
		// do bắt đầu tuần là chủ nhật (theo lịch Mỹ)
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
		return calendar;
	}

	public Calendar getDayInLastWeekOfMonth(int month) {
		Calendar calendar = getCurrentEndDay();
		calendar.set(Calendar.DAY_OF_WEEK, (calendar.getFirstDayOfWeek()) + 6);
		return calendar;
	}

	public Calendar getFirstDayOfYear(int year) {
		Calendar calendar = getCurrentStartDay();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.YEAR, year);
		return calendar;
	}

	public Calendar getLastDayOfYear(int year) {
		Calendar calendar = getCurrentEndDay();
		calendar.set(Calendar.DAY_OF_MONTH, 31);
		calendar.set(Calendar.MONTH, Calendar.DECEMBER);
		calendar.set(Calendar.YEAR, year);
		return calendar;
	}

	/** Set access is 0 in new start day **/
	@Scheduled(cron = "0 0 0 * * *")
	public void saveAutoCurrentDateToDB() {
		accessStatisticService.save(new AccessStatisticEntity(Calendar.getInstance(), 0));
	}

	// ---------------------------------------------

	private int getMonthDays(int month, int year) {
		int daysInMonth;
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			daysInMonth = 30;
		} else {
			if (month == 2) {
				daysInMonth = (year % 4 == 0) ? 29 : 28;
			} else {
				daysInMonth = 31;
			}
		}
		return daysInMonth;
	}

	public static void main(String[] args) {
		Calendar calendar = new DateUtil().getStartDay(8, Calendar.JANUARY, 2020);
		System.out.println(calendar.getTime());

		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(2020, 3, 23);
		System.out.println(calendar2.get(Calendar.DAY_OF_WEEK));
		System.out.println(calendar2.getFirstDayOfWeek());

	}
}
