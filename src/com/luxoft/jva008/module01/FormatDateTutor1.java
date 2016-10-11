package com.luxoft.jva008.module01;

import static com.luxoft.jva008.Logger.log;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

import org.junit.Test;

public class FormatDateTutor1 {
	
	/**
	 * Returns date in format dd.mm.yy
	 * Use Formatter
	 */
	public String getDateByFormatter(Date date) {
		Formatter fmt = new Formatter();
		String stringDate = fmt.format("%td.%tm.%ty", date, date, date).toString();
		fmt.close();
		return stringDate;
	}

	/**
	 * Returns date in format "27 of May, 2015"
	 * Use Formatter
	 */
	public String getDateString(Date date) {
		Formatter fmt = new Formatter(Locale.US);
		String stringDate = fmt.format("%td of %tB, %tY", date, date, date).toString(); 
		fmt.close();
		return stringDate;
	}
	
	/**
	 * Returns date in format "01.05.13"
	 * Use SimpleDateFormat
	 */
	public String getDateBySimpleDateFormat(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
		return dateFormat.format(date);
	}
	
	/**
	 * Returns date of type Date, converted from the line in format dd.mm.yy
	 * Use SimpleDateFormat, method parse()
	 */
	public Date parseDDMMYY(String s) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
		Date date = null;
		try {
			date = dateFormat.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
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
