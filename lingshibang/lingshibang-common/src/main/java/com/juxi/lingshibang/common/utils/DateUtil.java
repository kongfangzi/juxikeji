/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.juxi.lingshibang.common.utils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author ThinkGem
 * @version 2017-1-4
 */
public class DateUtil extends org.apache.commons.lang3.time.DateUtils {
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
	/** 时间格式(yyyy-MM) */
	public final static String DATE_PATTERN_MONGTH = "yyyy-MM";
	/**
	 * 时间格式(yyyy-MM-dd)
	 */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/**
	 * 时间格式(yyyy-MM-dd HH:mm:ss)
	 */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public final static String UTC_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public final static String UTC_TIME_ZONE = "UTC";
	/**
	 * 日期格式化 日期格式为：yyyy-MM-dd
	 *
	 * @param date 日期
	 * @return 返回yyyy-MM-dd格式日期
	 */
	public static String format(Date date) {
		return format(date, DATE_PATTERN);
	}

	/**
	 * 日期格式化 日期格式为：yyyy-MM-dd
	 *
	 * @param date    日期
	 * @param pattern 格式，如：DateUtils.DATE_TIME_PATTERN
	 * @return 返回yyyy-MM-dd格式日期
	 */
	public static String format(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			return df.format(date);
		}
		return null;
	}


	/**
	 * 字符串转换成日期
	 *
	 * @param strDate 日期字符串
	 * @param pattern 日期的格式，如：DateUtils.DATE_TIME_PATTERN
	 */
	public static Date stringToDate(String strDate, String pattern) {
		if (org.apache.commons.lang.StringUtils.isBlank(strDate)) {
			return null;
		}

		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
		return fmt.parseLocalDateTime(strDate).toDate();
	}

	/**
	 * 根据周数，获取开始日期、结束日期
	 *
	 * @param week 周期  0本周，-1上周，-2上上周，1下周，2下下周
	 * @return 返回date[0]开始日期、date[1]结束日期
	 */
	public static Date[] getWeekStartAndEnd(int week) {
		DateTime dateTime = new DateTime();
		LocalDate date = new LocalDate(dateTime.plusWeeks(week));

		date = date.dayOfWeek().withMinimumValue();
		Date beginDate = date.toDate();
		Date endDate = date.plusDays(6).toDate();
		return new Date[]{beginDate, endDate};
	}

	/**
	 * 对日期的【秒】进行加/减
	 *
	 * @param date    日期
	 * @param seconds 秒数，负数为减
	 * @return 加/减几秒后的日期
	 */
	public static Date addDateSeconds(Date date, int seconds) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusSeconds(seconds).toDate();
	}

	/**
	 * 对日期的【分钟】进行加/减
	 *
	 * @param date    日期
	 * @param minutes 分钟数，负数为减
	 * @return 加/减几分钟后的日期
	 */
	public static Date addDateMinutes(Date date, int minutes) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusMinutes(minutes).toDate();
	}

	/**
	 * 对日期的【小时】进行加/减
	 *
	 * @param date  日期
	 * @param hours 小时数，负数为减
	 * @return 加/减几小时后的日期
	 */
	public static Date addDateHours(Date date, int hours) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusHours(hours).toDate();
	}

	/**
	 * 对日期的【小时】进行加/减
	 *
	 * @param hours 小时数，负数为减
	 * @return 加/减几小时后的日期
	 */
	public static DateTime minusDateHours(DateTime dateTime, int hours) {
		return dateTime.minusHours(hours);
	}

	/**
	 * 对日期的【天】进行加/减
	 *
	 * @param date 日期
	 * @param days 天数，负数为减
	 * @return 加/减几天后的日期
	 */
	public static Date addDateDays(Date date, int days) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusDays(days).toDate();
	}

	/**
	 * 对日期的【周】进行加/减
	 *
	 * @param date  日期
	 * @param weeks 周数，负数为减
	 * @return 加/减几周后的日期
	 */
	public static Date addDateWeeks(Date date, int weeks) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusWeeks(weeks).toDate();
	}

	/**
	 * 对日期的【月】进行加/减
	 *
	 * @param date   日期
	 * @param months 月数，负数为减
	 * @return 加/减几月后的日期
	 */
	public static Date addDateMonths(Date date, int months) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusMonths(months).toDate();
	}

	/**
	 * 对日期的【年】进行加/减
	 *
	 * @param date  日期
	 * @param years 年数，负数为减
	 * @return 加/减几年后的日期
	 */
	public static Date addDateYears(Date date, int years) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusYears(years).toDate();
	}
    /**
     * 将返回的utc时间转换成本地时间
     *
     * @param utcTime
     * @return
     */
    public static String utc2local(String utcTime) {
        SimpleDateFormat sdf = new SimpleDateFormat(UTC_PATTERN);
        sdf.setTimeZone(TimeZone.getTimeZone(UTC_TIME_ZONE));
        Date UtcDate = null;
        try {
            UtcDate = sdf.parse(utcTime);
        } catch (Exception e) {
            return "";
        }
        SimpleDateFormat localFormater = new SimpleDateFormat(DATE_TIME_PATTERN);
        localFormater.setTimeZone(TimeZone.getDefault());
        String localTime = localFormater.format(UtcDate.getTime());
        return localTime;
    }
	/**
	 * 将utc时间转化成date类型
	 *
	 * @param utcTime
	 * @return
	 */
	public static Date utc2date(String utcTime) {
		SimpleDateFormat sf = new SimpleDateFormat(UTC_PATTERN, Locale.US);
		TimeZone utcZone = TimeZone.getTimeZone(UTC_TIME_ZONE);
		sf.setTimeZone(utcZone);
		Date date = null;
		try {
			date = sf.parse(utcTime);
		} catch (ParseException e) {
			logger.info(e.getMessage());
		}
		return date;
	}

	/**
	 * 得到前几个小时的时间
	 * hours为正值的时候加
	 * 为负值得时候为负
	 */
	public static Date plusHours(Date date, int hours) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);//date 换成已经已知的Date对象
		cal.add(Calendar.HOUR_OF_DAY, hours);// before 8 hour
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_PATTERN);
        return stringToDate(format.format(cal.getTime()),DATE_TIME_PATTERN);
	}
	
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH", "yyyy-MM",
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM/dd HH", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM.dd HH", "yyyy.MM", 
		"yyyy年MM月dd日", "yyyy年MM月dd日 HH时mm分ss秒", "yyyy年MM月dd日 HH时mm分", "yyyy年MM月dd日 HH时", "yyyy年MM月",
		"yyyy"};
	
	/**
	 * 得到日期字符串 ，转换格式（yyyy-MM-dd）
	 */
	public static String formatDate(Date date) {
		return formatDate(date, "yyyy-MM-dd");
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(long dateTime, String pattern) {
		return formatDate(new Date(dateTime), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, String pattern) {
		String formatDate = null;
		if (date != null){
			if (StringUtils.isBlank(pattern)) {
				pattern = "yyyy-MM-dd";
			}
			formatDate = FastDateFormat.getInstance(pattern).format(date);
		}
		return formatDate;
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}
    
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return FastDateFormat.getInstance(pattern).format(new Date());
	}
	
	/**
	 * 得到当前日期前后多少天，月，年的日期字符串
	 * @param pattern 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 * @param amont 数量，前为负数，后为正数
	 * @param type 类型，可参考Calendar的常量(如：Calendar.HOUR、Calendar.MINUTE、Calendar.SECOND)
	 * @return
	 */
	public static String getDate(String pattern, int amont, int type) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(type, amont);
		return FastDateFormat.getInstance(pattern).format(calendar.getTime());
	}
	
	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 日期型字符串转化为日期 格式   see to DateUtils#parsePatterns
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = System.currentTimeMillis()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = System.currentTimeMillis()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = System.currentTimeMillis()-date.getTime();
		return t/(60*1000);
	}
    
	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	/*public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}
	*/
	/**
	 * 获取某月有几天
	 * @param date 日期
	 * @return 天数
	 */
	public static int getMonthHasDays(Date date){
//		String yyyyMM = new SimpleDateFormat("yyyyMM").format(date);
		String yyyyMM = FastDateFormat.getInstance("yyyyMM").format(date);
		String year = yyyyMM.substring(0, 4);
		String month = yyyyMM.substring(4, 6);
		String day31 = ",01,03,05,07,08,10,12,";
		String day30 = "04,06,09,11";
		int day = 0;
		if (day31.contains(month)) {
			day = 31;
		} else if (day30.contains(month)) {
			day = 30;
		} else {
			int y = Integer.parseInt(year);
			if ((y % 4 == 0 && (y % 100 != 0)) || y % 400 == 0) {
				day = 29;
			} else {
				day = 28;
			}
		}
		return day;
	}
	
	/**
	 * 获取日期是当年的第几周
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date){
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获取一天的开始时间（如：2015-11-3 00:00:00.000）
	 * @param date 日期
	 * @return
	 */
	public static Date getOfDayFirst(Date date) {
		if (date == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 获取一天的最后时间（如：2015-11-3 23:59:59.999）
	 * @param date 日期
	 * @return
	 */
	public static Date getOfDayLast(Date date) {
		if (date == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * 将date类型格式化之后返回date类型
	 * @return
	 */
	public static Date d2d(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
		String d = sdf.format(date); //转换成字符串类型
		try {
			date = sdf.parse(d); //转回Date类型
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return date;
	}

	/**
	 * 获取服务器启动时间
	 * @param
	 * @return
	 */
	public static Date getServerStartDate(){
		long time = ManagementFactory.getRuntimeMXBean().getStartTime();
		return new Date(time);
	}
	
	/**
	 * 格式化为日期范围字符串
	 * @param beginDate 2018-01-01
	 * @param endDate 2018-01-31
	 * @return 2018-01-01 ~ 2018-01-31
	 * @author ThinkGem
	 */
	public static String formatDateBetweenString(Date beginDate, Date endDate){
		String begin = DateUtil.formatDate(beginDate);
		String end = DateUtil.formatDate(endDate);
		if (StringUtils.isNoneBlank(begin, end)){
			return begin + " ~ " + end;
		}
		return null;
	}
	
	/**
	 * 解析日期范围字符串为日期对象
	 * @param dateString 2018-01-01 ~ 2018-01-31
	 * @return new Date[]{2018-01-01, 2018-01-31}
	 * @author ThinkGem
	 */
	public static Date[] parseDateBetweenString(String dateString) {
		Date beginDate = null;
		Date endDate = null;
		if (StringUtils.isNotBlank(dateString)) {
			String[] ss = StringUtils.split(dateString, "~");
			if (ss != null && ss.length == 2) {
				String begin = StringUtils.trim(ss[0]);
				String end = StringUtils.trim(ss[1]);
				if (StringUtils.isNoneBlank(begin, end)) {
					beginDate = DateUtil.parseDate(begin);
					endDate = DateUtil.parseDate(end);
				}
			}
		}
		return new Date[]{beginDate, endDate};
	}
}
