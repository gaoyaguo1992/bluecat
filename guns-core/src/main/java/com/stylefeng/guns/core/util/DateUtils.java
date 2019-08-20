package com.stylefeng.guns.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.stylefeng.guns.core.lambda.DateOperator;
import com.stylefeng.guns.core.model.OperatorTypeEnum;
import com.stylefeng.guns.core.model.TimeGapBO;

/**
 * <p>
 * 处理日期的工具类,避免对工具类jar的整个引用
 * </P>
 */
public abstract class DateUtils {
    public static final String SHORT_FORMAT = "yyyyMMdd";
    public static final String NORMAL_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DDMMYY = "yyMMdd";
    public static final String JS_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
    public static final String SETTLEMENT_FORMAT = "yyyy-MM-dd";
    public static final String MMDDHHMM_FORMAT = "MM-dd HH:mm";
    public static final String LONG_FORMAT = "yyyyMMddHHmmss";
    public static final String YYMM = "yyMM";
    public static final String DDMM = "MMdd";

    public static void main(String[] args) {
        System.out.println(getOtherDate(new Date(), SETTLEMENT_FORMAT, 0));
        System.out.println(getOtherDate(new Date(), SETTLEMENT_FORMAT, -1));
    }

    /**
     * 返回日时分秒
     *
     * @param second
     * @return
     */
    public static String secondToTime(long second) {
        long days = second / 86400;//转换天数
        second = second % 86400;//剩余秒数
        long hours = second / 3600;//转换小时数
        second = second % 3600;//剩余秒数
        long minutes = second / 60;//转换分钟
        second = second % 60;//剩余秒数
        if (0 < days) {
            return days + "天" + hours + "小时" + minutes + "分" + second + "秒";
        } else {
            return hours + "小时" + minutes + "分" + second + "秒";
        }
    }

    /**
     * 将字符串日期，转换成指定格式字符日期
     *
     * @param targetDateStr 转换后的日期字符串
     * @param sourceDateStr 要转换的日期字符串
     * @param formatStr     要转换的日期字符串格式
     * @return
     * @throws ParseException
     */
    public static String formatDateStrFromDateStr(String targetDateStr, String sourceDateStr, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        SimpleDateFormat format1 = new SimpleDateFormat(targetDateStr);
        Date date;
        try {
            date = format.parse(sourceDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return format1.format(date);
    }

    /**
     * 判断两个时间段时否存在交集，true有交集 false 无交集 注意：顺序不能乱
     *
     * @param startdate1 时间段1开始
     * @param enddate1   时间段1结束
     * @param startdate2 时间段2开始
     * @param enddate2   时间段2结束
     * @return true有交集 false 无交集
     */
    public static boolean isOverlap(String startdate1, String enddate1, String startdate2, String enddate2) {
        SimpleDateFormat format = new SimpleDateFormat(SETTLEMENT_FORMAT);
        Date leftStartDate = null;
        Date leftEndDate = null;
        Date rightStartDate = null;
        Date rightEndDate = null;
        try {
            leftStartDate = format.parse(startdate1);
            leftEndDate = format.parse(enddate1);
            rightStartDate = format.parse(startdate2);
            rightEndDate = format.parse(enddate2);
        } catch (ParseException e) {
            return false;
        }

        return ((leftStartDate.getTime() > rightStartDate.getTime())
                && leftStartDate.getTime() < rightEndDate.getTime())
                || ((leftStartDate.getTime() > rightStartDate.getTime())
                && leftStartDate.getTime() < rightEndDate.getTime())
                || ((rightStartDate.getTime() >= leftStartDate.getTime())
                && rightStartDate.getTime() < leftEndDate.getTime())
                || ((rightStartDate.getTime() > leftStartDate.getTime())
                && rightStartDate.getTime() < leftEndDate.getTime());

    }

    /**
     * 根据传入date, 返回二十四进制小时
     */
    public static int getHoursOfDate(Date date) {
        if (date == null) {
            return 0;
        }
        SimpleDateFormat dateFm = new SimpleDateFormat(NORMAL_FORMAT);
        String dateFormatStr = dateFm.format(date);
        int index = dateFormatStr.indexOf(":");
        String hours = dateFormatStr.substring(index - 2, index);

        return Integer.parseInt(hours);
    }

    /**
     * 判断传入时间是否为周六周日
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
     */
    public static String getCurrentDate(String formatPattern) {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(formatPattern);
        return df.format(date);
    }

    public static String getFormatDate(String formatPattern, Date date) {

        DateFormat df = new SimpleDateFormat(formatPattern);
        return df.format(date);
    }

    public static Date getCheckDate() {
        return new Date();
    }

    public static Date getDateByFormat(String formatPattern, String dateStr) throws ParseException {

        DateFormat df = new SimpleDateFormat(formatPattern);
        return df.parse(dateStr);
    }

    /**
     * 根据输入起止日期来获取该范围内所有的日期（字符串类型）
     *
     * @param beginStr
     * @param endStr
     * @return
     * @date 2017年4月6日17:45:17
     * @author  Alan.lv
     */
    public static String[] getDateRangeArray(String beginStr, String endStr) {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date dateBegin = null;
        Date dateEnd = null;
        try {
            dateBegin = format.parse(beginStr);
            dateEnd = format.parse(endStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Date> list = getDatesBetweenTwoDate(dateBegin, dateEnd);
        String[] dateArray = new String[list.size()];
        SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd");
        for (int i = 0; i < list.size(); i++) {
            dateArray[i] = format2.format(list.get(i));
        }
        return dateArray;
    }

    /**
     * <p>
     * 获取指定日期与系统当前时间做对比
     * </p>
     */
    public static boolean compareReferDateWithNow(Date referDate) {
        Date add = addDay(referDate, 10, 1);
        Date nowDate = new Date();
        return nowDate.getTime() >= add.getTime();
    }

    public static String getHMSWithSeconds(int seconds) {
        String hour = seconds / (60 * 60) + "小时 ";
        String min = (seconds % (60 * 60)) / 60 + "分钟 ";
        String sec = ((seconds % (60 * 60)) % 60) + "秒";
        return hour + min + sec;
    }

    /**
     * 传入date，按需要转换时间格式
     *
     * @param fromDate
     * @param toFormStr
     * @return
     */
    public static String formatDate(Date fromDate, String toFormStr) {
        if (StringUtils.isEmpty(toFormStr)) {
            return null;
        }
        if (fromDate == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(toFormStr);
        try {
            return format.format(fromDate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 秒转化为天小时分秒字符串
     *
     * @param seconds
     * @return String
     */
    public static String formatSeconds(Long seconds) {
        String timeStr = seconds + "秒";
        if (seconds > 60) {
            Long days = seconds / (3600 * 24);
            Long tmpTime = seconds % (3600 * 24);
            Long hours = tmpTime / (3600);
            tmpTime = tmpTime % (3600);
            Long minutes = tmpTime / (60);
            Long sed = tmpTime % (60);
            return String.format("%d天%d时%d分%d秒", days, hours, minutes, sed);
        }
        return timeStr;
    }

    /**
     * 根据输入起止日期来获取该范围内所有的日期（日期类型）
     */
    public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {
        List<Date> lDate = new ArrayList<Date>();
        lDate.add(beginDate);// 把开始时间加入集合
        Calendar cal = Calendar.getInstance(); // 使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(beginDate);
        boolean bContinue = true;
        while (bContinue) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.DAY_OF_MONTH, 1);
            // 测试此日期是否在指定日期之后
            if (endDate.after(cal.getTime())) {
                lDate.add(cal.getTime());
            } else {
                break;
            }
        }
        lDate.add(endDate);// 把结束时间加入集合
        return lDate;
    }

    public static int getMinsDiff(Date max, Date min) {
        return (int) (max.getTime() - min.getTime()) / (1000 * 60);
    }

    /**
     * 根据输入的起止日期计算跨越哪些周
     *
     * @return
     */
    public static String[] getWeekByDay(String beginTime, String endTime) {

        int range = getWeek(endTime) - getWeek(beginTime); // 计算起止日期的周数的差值

        String week[] = new String[1 + range];

        week[0] = beginTime.substring(0, 4) + getWeek(beginTime);

        for (int i = 1; i < week.length - 1; i++) {
            week[i] = beginTime.substring(0, 4) + (getWeek(beginTime) + i);
        }

        week[range] = endTime.substring(0, 4) + getWeek(endTime);

        return week;
    }

    /**
     * DATE_FORMAT(ci.reg_time,'%Y%u') = '201713'
     * mysql统计周数的规则是按中国人的习惯，但是calendar统计周是按西方人的习惯，因此要对周进行处理
     *
     * @return
     */
    public static int getWeek(String time) {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            cal.setTime(format.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        // 根据中国人对于星期的常用表达方式，如果当天是周日，则当前获取的周数-1，即归并到上周的数据中
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            week = week - 1;
        }
        return week;
    }

    /**
     * 获取前一天的日期，格式为yyyyMMdd
     *
     * @return
     */
    public static String getYesterdayDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String mDateTime = formatter.format(c.getTime());
        return mDateTime;
    }

    /**
     * 获取前一天的日期，格式为yyyyMMdd
     *
     * @return
     */
    public static Date getTomorrowDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    /**
     * 获取指定日期前一天的日期，格式为yyyyMMdd
     *
     * @return
     */
    public static String getYesterdayDate(Date now) {
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String mDateTime = formatter.format(c.getTime());
        return mDateTime;
    }

    /**
     * 获取指定日期前后的日期，格式为自定义
     *
     * @param now           传入指定时间
     * @param dateFormatStr 需要返回的时间字符串格式
     * @param index         -1 为指定日期的前一天，1为指定日期的后一天，以此类推
     * @return
     */
    public static String getOtherDate(Date now, String dateFormatStr, int index) {
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.DAY_OF_MONTH, index);
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormatStr);
        String mDateTime = formatter.format(c.getTime());
        return mDateTime;
    }

    /**
     * 获取指定日期前一天的日期 格式自定义
     *
     * @param now
     * @param dateFormat 返回日期字符串格式
     * @return
     */
    public static String getYesterdayDate(Date now, String dateFormat) {
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        String mDateTime = formatter.format(c.getTime());
        return mDateTime;
    }

    /**
     * 获取指定日期后一天的日期 格式自定义
     *
     * @param now
     * @param dateFormat 返回日期字符串格式
     * @return
     */
    public static String getTomorrowDate(Date now, String dateFormat) {
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        String mDateTime = formatter.format(c.getTime());
        return mDateTime;
    }

    /**
     * 获取指定日期前一天的日期 格式自定义
     *
     * @param now
     * @param dateFormat 返回日期字符串格式
     * @return
     */
    public static String getYesterdayDate(String dateStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(dateStr));
        c.add(Calendar.DATE, -1);
        Date d = c.getTime();
        String day = format.format(d);
        return day;
    }

    /**
     * 获取指定日期前俩天的日期 格式自定义
     *
     * @param now
     * @param dateFormat 返回日期字符串格式
     * @return
     */
    public static String getBefYesterdayDate(Date now, String dateFormat) {
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.DAY_OF_MONTH, -2);
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        String mDateTime = formatter.format(c.getTime());
        return mDateTime;
    }

    /**
     * 获取输入起止日期范围内的所有日期
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static List<String> getDays(String beginDate, String endDate) {
        List<String> days = new ArrayList<String>();// 日期集合
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            long end = sdf.parse(endDate).getTime();
            long begin = sdf.parse(beginDate).getTime();
            while (begin <= end) {
                Date day = new Date();
                day.setTime(begin);
                begin += 3600 * 24 * 1000;
                days.add(sdf.format(day));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day + "天" + hour + "小时" + min + "分" + sec + "秒";
    }

    public static String getDistanceTimeByDayAndHour(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day + "天" + hour + "小时";
    }

    /**
     * 根据日获取该日是本年第几周
     *
     * @param time
     * @return
     */
    public static String getWeekTime(String time) {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            cal.setTime(format.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        // 根据中国人对于星期的常用表达方式，如果当天是周日，则当前获取的周数-1，即归并到上周的数据中
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            week = week - 1;
        }
        String weekTime = time.substring(0, 4) + week;
        return weekTime;
    }

    /**
     * 根据输入的起止日期遍历日期范围内所有的月份
     *
     * @param beginDate
     * @param endData
     * @return
     */
    public static List<String> getAllMonths(String beginDate, String endData) {
        List<String> monthsList = null;
        try {
            Date d1 = new SimpleDateFormat("yyyyMM").parse(beginDate);// 定义起始日期
            Date d2 = new SimpleDateFormat("yyyyMM").parse(endData);
            Calendar dd = Calendar.getInstance();// 定义日期实例
            monthsList = new ArrayList<String>();
            dd.setTime(d1);// 设置日期起始时间
            do {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
                String str = sdf.format(dd.getTime());
                monthsList.add(str);
                dd.add(Calendar.MONTH, 1);// 进行当前日期月份加1
            } while (dd.getTime().before(d2) || dd.getTime().equals(d2)); // 判断是否到结束日期
            return monthsList;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return monthsList;
    }

    /**
     * Date类型转Calendar
     *
     * @param date
     * @return
     */
    public static Calendar dateToCalendar(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 验证是否为一个月最后一天
     *
     * @param date
     * @return
     */
    public static boolean isLastByMonth(Calendar calendar) {
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return calendar.get(Calendar.DAY_OF_MONTH) == maxDay;
    }

    /**
     * 验证是否为一年最后一天
     *
     * @param date
     * @return
     */
    public static boolean isLastByYear(Calendar calendar) {
        int maxMonth = calendar.getActualMaximum(Calendar.MONTH);
        return calendar.get(Calendar.MONTH) == maxMonth && isLastByMonth(calendar);
    }

    /**
     * 获取给定时间所处星期的第一时间
     *
     * @param date
     * @return
     */
    public static Calendar getWeekFirstDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
     * 获取第一时间点 当前年,不可变更
     *
     * @param field Calendar.field
     * @return
     */
    public static Calendar getFirstDate(int field, Date date) {
        if (field == Calendar.DAY_OF_WEEK) {
            return getWeekFirstDate(date);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        switch (field) {
            case Calendar.YEAR:
                calendar.set(Calendar.MONTH, 0);
            case Calendar.MONTH:
                calendar.set(Calendar.DAY_OF_MONTH, 1);
            case Calendar.DAY_OF_MONTH:
                calendar.set(Calendar.HOUR_OF_DAY, 0);
            case Calendar.HOUR_OF_DAY:
                calendar.set(Calendar.MINUTE, 0);
            case Calendar.MINUTE:
                calendar.set(Calendar.SECOND, 0);
            case Calendar.SECOND:
                calendar.set(Calendar.MILLISECOND, 0);
            default:
                break;
        }
        return calendar;
    }

    /**
     * <p>
     * 获取上个月第一天
     * </p>
     *
     * @return
     */
    public static String getLastMonthFirstCheckDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        int lastMonthMaxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), lastMonthMaxDay, 23, 59, 59);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-01");
        String gtime2 = sdf2.format(c.getTime()); // 上月第一天
        return gtime2;
    }

    /**
     * <p>
     * 获取上个月最后一天
     * </p>
     *
     * @return
     */
    public static String getLastMonthLastCheckDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int lastMonthMaxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), lastMonthMaxDay, 23, 59, 59);
        String gtime = sdf.format(c.getTime()); // 上月最后一天
        return gtime;
    }

    /**
     * 获取最后时间点 当前年,不可变更
     *
     * @param field Calendar.field
     * @return
     */
    public static Calendar getLastDate(int field, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        switch (field) {
            case Calendar.YEAR:
                calendar.set(Calendar.MONTH, 11);
            case Calendar.MONTH:
                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DATE));
            case Calendar.DAY_OF_MONTH:
                calendar.set(Calendar.HOUR_OF_DAY, 23);
            case Calendar.HOUR_OF_DAY:
                calendar.set(Calendar.MINUTE, 59);
            case Calendar.MINUTE:
                calendar.set(Calendar.SECOND, 59);
            case Calendar.SECOND:
                calendar.set(Calendar.MILLISECOND, 999);
            default:
                break;
        }
        return calendar;
    }

    /**
     * 获取次时间点,如次月,次日
     *
     * @param field                    Calendar.field
     * @param calendar为:getFirstDate()
     * @return
     */
    public static Calendar addDate(Date date, int field, int add) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, add);
        return calendar;
    }

    /**
     * 遍历开始日期到结束日期的所有中间时段
     *
     * @param startDate
     * @param endDate
     * @param field
     * @return
     */
    public static boolean foreach(Date startDate, Date endDate, int field, int interval, DateOperator operator) {
        if (startDate == null || endDate == null) {
            return true;
        }
        while (startDate.getTime() <= endDate.getTime()) {
            boolean res = operator.operator(startDate);
            if (!res) {
                return res;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(field, interval);
            startDate = calendar.getTime();
        }
        return true;
    }


    /**
     * <p>
     * 根据指定日期和天数，获取相隔日期的format字符串格式
     * </p>
     */
    public static String getAppointDayStr(Date date, int days, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        date = calendar.getTime();
        return formatDate(date, format);
    }

    public static TimeGapBO getTimeGap(String begin, String end) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(begin);
            two = df.parse(end);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        TimeGapBO timeGapBO = new TimeGapBO(day, hour, min, sec);
        return timeGapBO;
    }

    /**
     * 从年开始往下比较 如field为Calendar.DAY_OF_MONTH,则比较年月日都相等
     *
     * @param src
     * @param target
     * @param field
     * @return
     */
    public static int compareTo(Calendar src, Calendar target, int field) {
        int result = compare(src, target, Calendar.YEAR);
        if (result != 0 || Calendar.YEAR == field) {
            return result;
        }
        result = compare(src, target, Calendar.MONTH);
        if (result != 0 || Calendar.MONTH == field) {
            return result;
        }
        result = compare(src, target, Calendar.DAY_OF_MONTH);
        if (result != 0 || Calendar.DAY_OF_MONTH == field) {
            return result;
        }
        result = compare(src, target, Calendar.HOUR_OF_DAY);
        if (result != 0 || Calendar.HOUR_OF_DAY == field) {
            return result;
        }
        result = compare(src, target, Calendar.MINUTE);
        if (result != 0 || Calendar.MINUTE == field) {
            return result;
        }
        result = compare(src, target, Calendar.SECOND);
        if (result != 0 || Calendar.SECOND == field) {
            return result;
        }
        result = compare(src, target, Calendar.MILLISECOND);
        if (result != 0 || Calendar.MILLISECOND == field) {
            return result;
        }
        return result;
    }

    /**
     * 仅比较对应field是否一致 如field为Calendar.DAY_OF_MONTH,则仅比较日是否相等
     *
     * @param src
     * @param target
     * @param field
     * @return
     */
    public static int compare(Calendar src, Calendar target, int field) {
        if (src == null && target == null) {
            return 0;
        }
        if (src == null) {
            return -1;
        }
        if (target == null) {
            return 1;
        }
        int srcFieldValue = src.get(field);
        int tarFieldValue = target.get(field);
        if (srcFieldValue == tarFieldValue) {
            return 0;
        }
        if (srcFieldValue > tarFieldValue) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * 获取当前时间所在的月份第一天的0点0分0秒
     *
     * @return
     */
    public static String getFirstDayTimeOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        // 将小时至0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        // 将分钟至0
        calendar.set(Calendar.MINUTE, 0);
        // 将秒至0
        calendar.set(Calendar.SECOND, 0);
        // 将毫秒至0
        calendar.set(Calendar.MILLISECOND, 0);
        // 获得当前月第一天
        Date sdate = calendar.getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(sdate);
    }

    /**
     * 格式化当前时间
     *
     * @param date
     * @return
     */
    public static String getNowTime(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    /**
     * 获取当前时间一周前的时间
     *
     * @param formatPattern 传入返回时间字符串格式
     * @return
     */
    public static String getOneWeekAgoTime(String formatPattern) {
        SimpleDateFormat format = new SimpleDateFormat(formatPattern);
        Calendar c = Calendar.getInstance();
        // 过去七天
        c.setTime(new Date());
        c.add(Calendar.DATE, -7);
        Date d = c.getTime();
        String day = format.format(d);
        return day;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     */
    public static int daysBetween(Date smdate, Date bdate) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算两个日期之间相差的天小时分钟数
     *
     * @param smdate
     * @param bdate
     * @return
     */
    public static String daysTimeGap(Date smdate, Date bdate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = bdate.getTime() - smdate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 如果返回1 DATE1 在 DATE2 后，如果返回-1 DATE1 在 DATE2 前，返回0则在同一时间
     *
     * @param DATE1
     * @param DATE2
     * @param dateFormatStr 可指定比较的日期格式
     * @return
     */
    public static int compare_date(String DATE1, String DATE2, String dateFormatStr) {

        DateFormat df = new SimpleDateFormat(dateFormatStr);
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 得到指定dateStr天前的i天数
     *
     * @return yyyy-MM-dd
     */
    public static String sevenDaysAgo(String dateStr, int i) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(dateStr));
        c.add(Calendar.DATE, -i + 1);
        Date d = c.getTime();
        String day = format.format(d);
        return day;
    }

    /**
     * 得到距此刻多少天后或者前的日期
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static Date getDesignatedDate(int i) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, +i);
        Date d = c.getTime();
        // String day = format.format(d);
        return d;
    }

    /**
     * <p>
     * 指定时间往后增加秒数
     * </p>
     *
     * @param seconds
     * @return
     */
    public static Date getAddSecondDate(Date sourceDate, int seconds) {
        Calendar c = Calendar.getInstance();
        c.setTime(sourceDate);
        c.add(Calendar.SECOND, seconds);
        Date d = c.getTime();
        return d;
    }

    /**
     * <p>
     * 将待处理的日期做加减运算
     * </p>
     *
     * @param sourceDay:要处理的日期
     * @param type:10往后加,11:往前减
     * @param days:具体加的天数或者减的天数
     * @return 返回指定日期
     */
    public static Date addDay(Date sourceDay, int type, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(sourceDay);
        if (OperatorTypeEnum.ADD.getCode() == type) {
            c.add(Calendar.DAY_OF_MONTH, days);
        } else {
            c.add(Calendar.DAY_OF_MONTH, -days);
        }
        Date targetDate = c.getTime();
        return targetDate;
    }

    /**
     * <p>
     * 根据指定日期和天数，获取相隔日期
     * </p>
     *
     * @param date
     * @param days 正负代表前几天，后几天
     */
    public static Date getAppointDay(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        date = calendar.getTime();
        return date;
    }

    /**
     * <p>
     * 判断指定的时间是否在指定时间段内
     * </p>
     */
    public static boolean belongCalendar(Date nowTime, String beginTimeStr, String endTimeStr) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");// 设置日期格式
        Date nowDate = null;
        Date beginTime = null;
        Date endTime = null;
        try {
            nowDate = df.parse(df.format(nowTime));
            beginTime = df.parse(beginTimeStr);
            endTime = df.parse(endTimeStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (nowTime.getTime() == beginTime.getTime()) {
            return true;
        }

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar date = Calendar.getInstance();
        date.setTime(nowDate);
        return date.after(begin) && date.before(end);
    }

    /**
     * 获取本月的最后一天
     *
     * @return theLastDayOfThisMonth 本月最后一天
     */
    public static int getTheLastOfThisMonth() {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DATE, 1);
        date.add(Calendar.DATE, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String str = sdf.format(date.getTime());
        return Integer.valueOf(str);
    }

    /**
     * 获取现在是本月的第几天
     *
     * @return theDateOfThisMonth
     */
    public static int getNowDateOfThisMonth() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String str = sdf.format(now);
        return Integer.valueOf(str);
    }

    /**
     * 判断时间是不是在本月时间段之内
     *
     * @return boolean 是否在时间段内
     */
    public static boolean judgeNowBetweenPeriod(int startDay, int endDay) {
        if (endDay < startDay) {
            return false;
        }
        Calendar date = Calendar.getInstance();
        // 获取本月最大天数，获取本月的最后一天
        int lastDayOfThisMonth = date.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (endDay > lastDayOfThisMonth) { // 考虑到endDay是31天的情况
            endDay = lastDayOfThisMonth;
        }
        int now = getNowDateOfThisMonth();
        return now <= endDay && now >= startDay;
    }

    /**
     * 获取本月第一天的时间,精确到秒
     *
     * @return firstDayOfThisMonth
     */
    public static String getFirstDateOfTheMonth() {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DATE, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfThisMonth = sdf.format(date.getTime());
        firstDayOfThisMonth = firstDayOfThisMonth + " 00:00:00";
        return firstDayOfThisMonth;
    }

    /**
     * 获取本月最后一天的时间，精确到秒
     *
     * @return lastDayOfThisMonth
     */
    public static String getLastDateOfTheMonth() {
        Calendar date = Calendar.getInstance();
        // 获取本月最大天数
        int lastDay = date.getActualMaximum(Calendar.DAY_OF_MONTH);
        // 设置日历中月份的最大天数
        date.set(Calendar.DAY_OF_MONTH, lastDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfThisMonth = sdf.format(date.getTime());
        lastDayOfThisMonth = lastDayOfThisMonth + " 23:59:59";
        return lastDayOfThisMonth;
    }
}
