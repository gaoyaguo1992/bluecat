/**
 * Copyright (c) 2015-2016, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stylefeng.guns.core.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public class DateUtil {
	/**
	 * 短时间格式 yyyyMMdd
	 */
	public static final String SHORT_FORMAT = "yyyyMMdd";
	/**
	 * 长时间格式 yyyyMMddHHmmss
	 */
	public static final String LONG_FORMAT = "yyyyMMddHHmmss";
	/**
	 * 正常格式
	 */
	public static final String NORMAL_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/**
	 * ddmmyy
	 */
	public static final String DDMMYY = "yyMMdd";
	/**
	 * 年月
	 */
	public static final String YYMM = "yyMM";
	/**
	 * 日期月
	 */
	public static final String DDMM = "MMdd";
	/**
	 * 共享助手时间格式
	 */
	public static final String SHAREHELPER_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/**
	 * js对应的时间格式
	 */
	public static final String JS_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
	public static final String SETTLEMENT_FORMAT = "yyyy-MM-dd";

	/**
	 * 获取YYYY格式
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 获取YYYY格式
	 */
	public static String getYear(Date date) {
		return formatDate(date, "yyyy");
	}

	/**
	 * 获取YYYY-MM-DD格式
	 */
	public static String getDay() {
		return formatDate(new Date(), "yyyy-MM-dd");
	}

	/**
	 * 获取YYYY-MM-DD格式
	 */
	public static String getDay(Date date) {
		return formatDate(date, "yyyy-MM-dd");
	}

	/**
	 * 获取YYYYMMDD格式
	 */
	public static String getDays() {
		return formatDate(new Date(), "yyyyMMdd");
	}

	/**
	 * 获取YYYYMMDD格式
	 */
	public static String getDays(Date date) {
		return formatDate(date, "yyyyMMdd");
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 */
	public static String getTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss.SSS格式
	 */
	public static String getMsTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
	}

	/**
	 * 获取YYYYMMDDHHmmss格式
	 */
	public static String getAllTime() {
		return formatDate(new Date(), "yyyyMMddHHmmss");
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 */
	public static String getTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String formatDate(Date date, String pattern) {
		String formatDate = null;
		if (StringUtils.isNotBlank(pattern)) {
			formatDate = DateFormatUtils.format(date, pattern);
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 日期比较，如果s>=e 返回true 否则返回false)
	 *
	 * @author luguosui
	 */
	public static boolean compareDate(String s, String e) {
		if (parseDate(s) == null || parseDate(e) == null) {
			return false;
		}
		return parseDate(s).getTime() >= parseDate(e).getTime();
	}

	/**
	 * 格式化日期
	 */
	public static Date parseDate(String date) {
		return parse(date, "yyyy-MM-dd");
	}

	/**
	 * 格式化日期
	 */
	public static Date parseTimeMinutes(String date) {
		return parse(date, "yyyy-MM-dd HH:mm");
	}

	/**
	 * 格式化日期
	 */
	public static Date parseTime(String date) {
		return parse(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 格式化日期
	 */
	public static Date parse(String date, String pattern) {
		try {
			return DateUtils.parseDate(date, pattern);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 格式化日期
	 */
	public static String format(Date date, String pattern) {
		return DateFormatUtils.format(date, pattern);
	}

	/**
	 * 把日期转换为Timestamp
	 */
	public static Timestamp format(Date date) {
		return new Timestamp(date.getTime());
	}

	/**
	 * 校验日期是否合法
	 */
	public static boolean isValidDate(String s) {
		return parse(s, "yyyy-MM-dd HH:mm:ss") != null;
	}

	/**
	 * 校验日期是否合法
	 */
	public static boolean isValidDate(String s, String pattern) {
		return parse(s, pattern) != null;
	}

	public static int getDiffYear(String startTime, String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24))
					/ 365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}

	/**
	 * <li>功能描述：时间相减得到天数
	 */
	public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = null;
		Date endDate = null;

		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
		// System.out.println("相隔的天数="+day);

		return day;
	}

	/**
	 * 得到n天之后的日期
	 */
	public static String getAfterDayDate(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdfd.format(date);

		return dateStr;
	}

	/**
	 * 得到n天之后是周几
	 */
	public static String getAfterDayWeek(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String dateStr = sdf.format(date);

		return dateStr;
	}

	/**
	 * 根据传入date, 返回二十四进制小时
	 * 
	 * @param date
	 * @return date 当前的小时
	 */
	public static int getHoursOfDate(Date date) {
		if (date == null) {
			return 0;
		}
		SimpleDateFormat dateFm = new SimpleDateFormat(SHAREHELPER_TIME_FORMAT);
		String dateFormatStr = dateFm.format(date);
		int index = dateFormatStr.indexOf(":");
		String hours = dateFormatStr.substring(index - 2, index);

		return Integer.parseInt(hours);
	}

	/**
	 * 判断传入时间是否为周六周日
	 * 
	 * @param date
	 * @return true为周六周日 false 为周一到周五
	 */
	public static boolean isWeekendOfDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return w == 0 || w == 6;
    }

	/**
	 * <p>
	 * 根据指定的格式,获得当前日期的字符串形式
	 * </p>
	 * 
	 * @param formatPattern DateUtils.SHORT_FORMAT 或 DateUtils.LONG_FORMAT
	 *                      或DateUtils.NORMAL_FORMAT
	 */
	public static String getCurrentDate(String formatPattern) {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(formatPattern);
		return df.format(date);
	}

	/**
	 * 格式化时间...
	 * 
	 * @param formatPattern
	 * @param date
	 * @return
	 */
	public static String getFormatDate(String formatPattern, Date date) {
		DateFormat df = new SimpleDateFormat(formatPattern);
		return df.format(date);
	}

	/**
	 * 获取一个月前的时间字符串
	 * 
	 * @param formatPattern
	 * @return
	 */
	public static Date getBeforeMonth(String formatPattern) {
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		Calendar c = Calendar.getInstance();
		// 过去一月
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date m = c.getTime();
		String mon = format.format(m);
		return parse(mon, formatPattern);

	}

	/**
	 * 获取今天时间
	 * @param formatPattern
	 * @return
	 */
	public static Date getToday(String formatPattern) {
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		Date now = new Date();
		String nowStr = format.format(now);
		return parse(nowStr, formatPattern);

	}
}
