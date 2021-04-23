package br.ce.wcaquino.utils;

import java.util.Calendar;

public class DateUtils {
	
	public static Calendar getCurrentCalendar() {
		Calendar cal = Calendar.getInstance();
		int date = cal.get(Calendar.DATE);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		cal.setTimeInMillis(0);
		cal.set(year, month, date);
		
		return cal;
	}

}
