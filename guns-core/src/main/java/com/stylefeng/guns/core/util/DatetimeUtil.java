package com.stylefeng.guns.core.util;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatetimeUtil {

	public final static String DATETIME_PATTERN = "yyyyMMddHHmmss";
	
	public final static String TIME_STAMP_PATTERN = "yyyyMMddHHmmssSSS";

	public final static String DATE_PATTERN = "yyyyMMdd";

	public final static String TIME_PATTERN = "HHmmss";

	public final static String STANDARD_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public final static String STANDARD_DATETIME_PATTERN_HM = "yyyy-MM-dd HH:mm";

	public final static String STANDARD_DATE_PATTERN = "yyyy-MM-dd";

	public final static String STANDARD_TIME_PATTERN = "HH:mm:ss";

	public final static String STANDARD_DATETIME_PATTERN_SOLIDUS = "yyyy/MM/dd HH:mm:ss";

	public final static String STANDARD_DATETIME_PATTERN_SOLIDUS_HM = "yyyy/MM/dd HH:mm";

	public final static String STANDARD_DATE_PATTERN_SOLIDUS = "yyyy/MM/dd";

	private DatetimeUtil() {
		super();
	}

	public static Timestamp currentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static String currentDatetime() {
		return formatDate(new Date());
	}

	public static Timestamp parseDate(String dateStr, String pattern) {
		Date d = DatetimeUtil.parse(dateStr, pattern);
		return new Timestamp(d.getTime());
	}

	public static Timestamp parseDate(String dateStr) {
		return parseDate(dateStr, STANDARD_DATE_PATTERN);
	}

	public static java.sql.Date parseSQLDate(String dateStr, String pattern) {
		Date d = parse(dateStr, pattern);
		return new java.sql.Date(d.getTime());
	}

	public static java.sql.Date parseSQLDate(String dateStr) {
		Date d = parse(dateStr, STANDARD_DATE_PATTERN);
		return new java.sql.Date(d.getTime());
	}

	public static Timestamp getFutureTime(int month) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, month);
		return new Timestamp(c.getTimeInMillis());
	}

	/**
	 * 显示今天时间 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String today() {
		return formatDateTime(new Date());
	}
	
	/**
	 * 显示今天时间 
	 * @return yyyy-MM-dd
	 */
	public static String todayYyyyMMdd() {
		return formatDate(new Date(),STANDARD_DATE_PATTERN);
	}
	
	public static String tomorrowYyyyMMdd() {		 
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);
        Date d = c.getTime();     
		return formatDate(d,STANDARD_DATE_PATTERN);
	}
	/**
	 * 传入时间增加或减天数..
	 * @param dt
	 * @return
	 */
	public static Date addDayForDate(Date date,int days) {		 
		if(date==null){
			date=new Date();
		}
		if(days==0){
			return date;
		}
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        Date d = c.getTime();     
		return d;
	}
	/**
	 * 显示昨天时间 
	 * @return yyyy-MM-dd
	 */
	public static String yesterdayYyyyMMdd() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, - 1);
        Date d = c.getTime();
        String day = format.format(d);
        return day;
	}
	
	/**
	 * 显示前天时间 
	 * @return yyyy-MM-dd
	 */
	public static String beforeYesterdayYyyyMMdd() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, - 2);
        Date d = c.getTime();
        String day = format.format(d);
        return day;
	}
	
	/**
	 * 一周后
	 * @return yyyyMMdd
	 */
	public static String nextWeekYyyyMMdd() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        //一周后
        c.setTime(new Date());
        c.add(Calendar.DATE, + 7);
        Date d = c.getTime();
        String day = format.format(d);
        return day;
	}
	
	/**
	 * 一周前
	 * @return yyyyMMdd
	 */
	public static String lastWeekYyyyMMdd() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, - 7);
        Date d = c.getTime();
        String day = format.format(d);
        return day;
	}
	
	/**
	 * <p>多少天前</p>
	 * @param days 指定多少天前
	 * @return yyyy-MM-dd
	 */
	public static String yearMonthDaysAgo(int days) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, - days);
        Date d = c.getTime();
        String day = format.format(d);
        return day;
	}
	
	/**
	 * <p>多少天前</p>
	 * @param days 指定多少天前
	 * @return yyyyMMdd
	 */
	public static String yearMonthDays(int days) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, - days);
        Date d = c.getTime();
        String day = format.format(d);
        return day;
	}
	
	/**
	 * <p>多少天前,并且指定转换格式</p>
	 * @param days 指定多少天前
	 * @return yyyy-MM-dd
	 */
	public static String yearMonthDaysAndPattern(int days,String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, - days);
        Date d = c.getTime();
        String day = format.format(d);
        return day;
	}
	
	/**
	 * 一个月前
	 * @return yyyyMMdd
	 */
	public static String lastMonthYyyyMMdd() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, - 30);
        Date d = c.getTime();
        String day = format.format(d);
        return day;
	}
	
	public static String formatDate(Timestamp t) {
		return formatDate(new Date(t.getTime()));
	}

	public static String formatDate(Timestamp t, String pattern) {
		return formatDate(new Date(t.getTime()), STANDARD_DATE_PATTERN);
	}
	
	public static String formatDateTime(Timestamp t, String pattern) {
		return formatDate(new Date(t.getTime()), STANDARD_DATETIME_PATTERN);
	}

	/**
	 * 格式化日期 
	 * @param date
	 * @return yyyy-MM-dd
	 */
	public static String formatDate(Date date) {
		return formatDate(date, STANDARD_DATE_PATTERN);
	}

	/**
	 * 格式化日期
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String formatDateTime(Date date) {
		if(date==null){
			return "";
		}
		return formatDate(date, STANDARD_DATETIME_PATTERN);
	}

	/**
	 * 格式化日期
	 * @param date 日期
	 * @param pattern 格式
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		if (date == null)
			return null;
		DateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/**
	 * 解析日期
	 * @param dateStr yyyy-MM-dd
	 * @return
	 */
	public static Date parse(String dateStr) {
		return parse(dateStr, STANDARD_DATE_PATTERN);
	}
	
	/**
	 * 解析日期
	 * @param dateStr yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date parseTime(String dateStr){
		return parse(dateStr, STANDARD_DATETIME_PATTERN);
	}

	public static Date parse(String dateStr, String pattern) {

		try {
			DateFormat format = new SimpleDateFormat(pattern);
			return format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 当月的第一天
	 * 
	 * @return
	 */
	public static String firstDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return DatetimeUtil.formatDate(calendar.getTime()) + " 00:00:00";
	}

	/**
	 * 当月的最后一天
	 * 
	 * @return
	 */
	public static String lastDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		return DatetimeUtil.formatDate(calendar.getTime()) + " 23:59:59";
	}
	
	/**
	 * <p>距今多少天</p>
	 * @param +- days 指定多少天
	 */
	public static String getYearMonthDays(int days,String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, days);
        Date d = c.getTime();
        String day = format.format(d);
        return day;
	}
	
	/**
	 * 获取到昨日时间的00:00:00
	 * @param days
	 * @param pattern
	 * @return
	 */
	public static String getYearMonthDaysIsEarlyMorning(int days,String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
	    Calendar c = Calendar.getInstance();
	    c.setTime(new Date());
	    c.add(Calendar.DATE, -1);
	    c.set(Calendar.HOUR_OF_DAY, 0);
		//c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
	    Date d = c.getTime();
		return format.format(d);
	}
	
	/***
	 *  获取到日期的后一天 格式 yyyy-MM-dd
	 * @param time
	 * @return
	 */
	public static Date dayYyyyMMdd(Date time) {		 
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.add(Calendar.DATE, 1);
		return new Date(c.getTimeInMillis());
	}
	
	/**
	 * 获取到当前日期的 00:00:00
	 * @param time
	 * @return
	 */
	public static String getdayEarlyMorning(Date time ,String pattern) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		//calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(calendar.getTime());
	}
	
}
