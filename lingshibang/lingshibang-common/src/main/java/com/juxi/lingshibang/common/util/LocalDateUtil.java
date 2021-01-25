package com.juxi.lingshibang.common.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * 时间工具类
 * @author yks
 * @date 2019-11-21
 */
@UtilityClass
@Slf4j
public class LocalDateUtil {

    public static final String TIME_FORMATTER_1 = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMATTER_2 = "yyyy-MM-dd";
    public static final String TIME_FORMATTER_3 = "yyyyMMddHHmmss";
    public static final String TIME_FORMATTER_4 = "yyyyMMdd";
    public static final String TIME_FORMATTER_5 = "yyMMdd";
    public static final String TIME_FORMATTER_6 = "yyyyMMddHH";
    public static final int ONE_MONTH_DAY = 30;

    /**
     * 当天23:59:59
     */
    public static final String CURRENT_DAY_ZERO_TIME = " 23:59:59";

    /**
     * 当天 00:00:00
     */
    public static final String CURRENT_DAY_ZERO_TIME_1 = " 00:00:00";


    /**
     * 时间字符串格式化
     * @param dateTimeString
     * @return
     */
    public LocalDateTime parseDateTime(String dateTimeString) {
        Assert.isTrue(StringUtils.hasText(dateTimeString), "dateTimeString is null");
        return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(TIME_FORMATTER_1));
    }

    /**
     * 时间字符串格式化
     * @param dateTimeString
     * @return
     */
    public LocalDate parseDate(String dateTimeString) {
        Assert.isTrue(StringUtils.hasText(dateTimeString), "dateTimeString is null");
        return LocalDate.parse(dateTimeString, DateTimeFormatter.ofPattern(TIME_FORMATTER_2));
    }


    /**
     * 时间字符串格式化
     * @param localDateTime
     * @return
     */
    public String formatDateTime(LocalDateTime localDateTime) {
        Assert.notNull(localDateTime, "localDateTime is null");
        return localDateTime.format(DateTimeFormatter.ofPattern(TIME_FORMATTER_1));
    }


    /**
     * 时间字符串格式化
     * @param localDateTime
     * @param format
     * @return
     */
    public String formatDateTime(LocalDateTime localDateTime, String format) {
        Assert.notNull(localDateTime, "localDateTime is null");
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 时间字符串格式化
     * @param timeStr
     * @param format
     * @return
     */
    public String timeStr2LocalDateStr(String timeStr, String format) {
        Assert.isTrue(StringUtils.hasText(timeStr), "timeStr is null");
        log.info("timeStr before={}", timeStr);
        timeStr = timeStr.length() > 10 ? timeStr.substring(0, 10) : timeStr;
        log.info("timeStr after={}", timeStr);
        LocalDate localDate = LocalDate.parse(timeStr);
        return localDate.format(DateTimeFormatter.ofPattern(format));
    }


    /**
     * 将yyyy-MM-dd 转为秒
     * @param date
     * @return
     */
    public long parse2Seconds(String date) {
        return parseDateTime(date).toEpochSecond(ZoneOffset.of("+8"));
    }

    public LocalDateTime getDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * 获取当前的UNIX时间
     * @return
     */
    public long currentUnixTime() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
    }

    /**
     * 计算相差的天数
     * @param startDateTime
     * @param endDateTime
     * @return
     */
    public long daysBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return endDateTime.toLocalDate().toEpochDay() - startDateTime.toLocalDate().toEpochDay();
    }


    public String nowDateStr(String format) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 获取当前日期字符串
     * @return 20190102
     */
    public String currentDateStr() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(TIME_FORMATTER_4));
    }

    /**
     * 获取当前日期字符串
     * @return 190102
     */
    public String currentDateStr1() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(TIME_FORMATTER_5));
    }

    /**
     * 当天剩余时间（秒） 截止到当天23:59:59
     * @return
     */
    public long currentDaySecondLeft() {
        Long timeLeft = LocalDateTime.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                .concat(CURRENT_DAY_ZERO_TIME), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                .toEpochSecond(ZoneOffset.of("+8")) - LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        return timeLeft;
    }

    public String today() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Unix时间戳 转换成时间字符串
     * @param timestamp (秒)
     * @return
     */
    public String timeStamp2DateStr(long timestamp) {
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.ofHours(8));
        return dateTime.format(DateTimeFormatter.ofPattern(TIME_FORMATTER_1));
    }

    /**
     * Date转LocalDate
     * @param date
     */
    public static LocalDate date2LocalDate(Date date) {
        if (null == date) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * LocalDate转Date
     * @param localDate
     * @return
     */
    public static Date localDate2Date(LocalDate localDate) {
        if (null == localDate) {
            return null;
        }
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * Unix时间戳 转换成时间字符串
     * @param timestamp (秒)
     * @return
     */
    public LocalDateTime timeStamp2Date(long timestamp) {
        return LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.ofHours(8));
    }

    /**
     * 返回当天剩余多少秒
     * @return
     */
    public Long getSecondLeftCurrentDay() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime todayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        return ChronoUnit.SECONDS.between(now, todayEnd);
    }

    /**
     * Date转换为LocalDateTime
     * @param date
     */
    public LocalDateTime date2LocalDateTime(Date date) {
        Instant instant = date.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        ZoneId zoneId = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
        return instant.atZone(zoneId).toLocalDateTime();

    }

    /**
     * 两个时刻的秒数之差
     * @param startDateTime
     * @param endDateTime
     * @return 秒
     */
    public Long betweenSecond(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return ChronoUnit.SECONDS.between(startDateTime, endDateTime);
    }

    /**
     * 两个时刻的秒数之差
     * @param startDateTime
     * @param endDateTime
     * @return 秒
     */
    public Long betweenDays(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return ChronoUnit.DAYS.between(startDateTime, endDateTime);
    }


    /**
     * 获取指定时间的UNIX时间戳
     * @return
     */
    public long toUnixTime(LocalDateTime localDateTime) {
        return localDateTime.toEpochSecond(ZoneOffset.of("+8"));
    }

    /**
     * 时间转字符串
     * @param timeFormatter
     * @param localDateTime
     * @return
     */
    public String dateToStr(String timeFormatter, LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(timeFormatter));
    }

    /**
     * 一天最小时刻 2020-03-28 00:00:00
     * @param timeStr
     * @return
     */
    public LocalDateTime minDateTimeOfDay(String timeStr) {
        return LocalDateTime.of(LocalDate.parse(timeStr), LocalTime.MIN);
    }

    /**
     * 一天最大时刻 2020-03-28 23:59:59
     * @param timeStr
     * @return
     */
    public LocalDateTime maxDateTimeOfDay(String timeStr) {
        return LocalDateTime.of(LocalDate.parse(timeStr), LocalTime.MAX);
    }

    public static void main(String[] args) {
        /*Long timestamp = LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(8));
        System.out.println(timeStamp2DateStr(timestamp));*/

        /*String nowDateStr=LocalDateUtil.nowDateStr(
               LocalDateUtil.TIME_FORMATTER_1);
        log.info("nowDateStr:{}",nowDateStr);*/
        LocalDateTime midnight = LocalDateTime.ofInstant(new Date().toInstant(),
                ZoneId.systemDefault()).plusDays(1).withHour(0).withMinute(0)
                .withSecond(0).withNano(0);
        System.out.println(ChronoUnit.SECONDS.between(date2LocalDateTime(new Date()), midnight));
        System.out.println(dateToStr(TIME_FORMATTER_4, LocalDateTime.now()));
        System.out.println("----------------");
        System.out.println(maxDateTimeOfDay("2020-03-28"));
        System.out.println(minDateTimeOfDay("2020-03-28"));
        System.out.println(maxDateTimeOfDay("2020-03-28")
                .format(DateTimeFormatter.ofPattern(TIME_FORMATTER_1)));
        System.out.println(minDateTimeOfDay("2020-03-28")
                .format(DateTimeFormatter.ofPattern(TIME_FORMATTER_1)));
    }


}
