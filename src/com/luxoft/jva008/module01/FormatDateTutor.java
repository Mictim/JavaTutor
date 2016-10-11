package com.luxoft.jva008.module01;

import static com.luxoft.jva008.Logger.log;
import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class FormatDateTutor {
	
	/**
	 * Returns date in format dd.mm.yy
	 * Use Formatter
	 */
	public String getDateByFormatter(Date date) {
		return null;
	}

	/**
	 * Returns date in format "27 of May, 2015"
	 * Use Formatter
	 */
	public String getDateString(Date date) {
		return null;
	}
	
	/**
	 * Returns date in format "01.05.13"
	 * Use SimpleDateFormat
	 */
	public String getDateBySimpleDateFormat(Date date) {
		return null;
	}
	
	/**
	 * Returns date of type Date, converted from the line in format dd.mm.yy
	 * Use SimpleDateFormat, method parse()
	 */
	public Date parseDDMMYY(String s) {
		return null;
	}
	
	@Test
	public void testFormatDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 4, 1); // 1st of May, 2013
		Date date = cal.getTime();
		
		String dateByFormatter = getDateByFormatter(date);
		log("dateByFormatter: " + dateByFormatter);
		assertEquals(dateByFormatter, "01.05.13");
		
		String dateBySimpleDateFormat = getDateBySimpleDateFormat(date);
		log("dateBySimpleDateFormat: " + dateBySimpleDateFormat);
		assertEquals(dateBySimpleDateFormat, "01.05.13");

		System.out.println(getDateString(new Date()));
	}
	
	@Test
	public void testParseDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 4, 1, 0, 0, 0); // 1st of May, 2013
		cal.set(Calendar.MILLISECOND, 0);
		Date date = cal.getTime();
		Date d = parseDDMMYY("01.05.13");
		assertEquals(date, d);
	}

}
