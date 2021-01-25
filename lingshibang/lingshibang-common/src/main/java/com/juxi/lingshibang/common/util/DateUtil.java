package com.juxi.lingshibang.common.util;

import com.juxi.lingshibang.common.util.StringUtil;
import com.juxi.lingshibang.common.util.NumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @version 1.0
 * @date 2017-05-19
 */
@Slf4j
public final class DateUtil {
    public static final String  DATE_FORMAT_PATTERN_1 = "yyyy-MM-dd HH:mm:ss";
    public static final String  DATE_FORMAT_PATTERN_2 = "yyyyMMddHHmmss";
    public static final String  DATE_FORMAT_PATTERN_3 = "yyyyMMdd";
    public static final String  DATE_FORMAT_PATTERN_4 = "yyMMdd";
    private final static String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
    // yyyy-MM-dd
    public static Date strToDate(String str) throws ParseException {
        if (StringUtil.isNotBlank(str)) {
            return DateUtils.parseDate(str, DATE_FORMAT_PATTERN);
        }
        return null;
    }

    public static boolean isBirthday(Date birthday) {
        return birthday == null ? false : isBirthday(birthday.getTime());
    }
    public static boolean isBirthday(long birthday) {
        boolean b = false;
        String now = DateFormatUtils.format(System.currentTimeMillis(), "MM-dd");
        String birth = DateFormatUtils.format(birthday, "MM-dd");
        b = StringUtil.equals(now, birth);
        return b;
    }
    //由出生日期获得年龄
    public static int getAge(Date birthDay) throws Exception {
        if (birthDay == null) {
            return -1;
        }
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            }else{
                age--;
            }
        }
        return age;
    }
    public static int getAge(long birthday) {

        Calendar cal = Calendar.getInstance();

        if (System.currentTimeMillis() < birthday) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTimeInMillis(birthday);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            }else{
                age--;
            }
        }
        return age;
    }

    public static String timeSpan(Date st, Date et) {
        if (st == null) {
            return "0";
        }
        if (et == null) {
            et = new Date();
        }
        long stt = st.getTime();
        long ett = et.getTime();
        Long t = Math.round((ett - stt) / (1000 * 60d));
        if (t < 0) {
            t = 0L;
        }
        return t.toString();
    }

    public static long timeSpanNum(Date st, Date et) {
        if (st == null) {
            return 0;
        }
        if (et == null) {
            et = new Date();
        }
        long stt = st.getTime();
        long ett = et.getTime();
        Long t = Math.round((ett - stt) / (1000 * 60d));
        if (t < 0) {
            t = 0L;
        }
        return t;
    }

    public static Pair<Date, Date> getDateStartEnd(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        Date sd = calendar.getTime();
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,999);
        Date ed = calendar.getTime();
        return Pair.of(sd, ed);
    }

    private static final String DATE_INT_PATTERN = "yyyyMMdd00";
    public static int getDateIntValue(Date date) {
        if (date == null) {
            date = new Date();
        }
        String str = DateFormatUtils.format(date, DATE_INT_PATTERN);
        return NumberUtil.toInt(str, 0);
    }
    public final static String format(Date date, String pattern) {
        if (null == date || null == pattern) return "";
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 日期是否相等，统配
     * @param d1
     * @param d2
     * @return
     */
    public static boolean equalDate(String d1, String d2) {
        String[] date1 = StringUtil.split(d1, " ");
        String[] date2 = StringUtil.split(d2, " ");
        if (date1.length != date2.length) {
            return false;
        }
        int len = date1.length;
        for (int i = 0; i < len; i++) {
            String u1 = date1[i];
            String u2 = date2[i];
            if (!StringUtil.equals(u1, "*") && !StringUtil.equals(u2, "*")
                    && !StringUtil.equals(u1, u2)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 两个日期的相差天数
     * @param startDate
     * @param endDate
     * @return
     */
    public static long daysOfTwo(Date startDate,Date endDate){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        long days=(endDate.getTime()-startDate.getTime())/(1000*3600*24);
        return days;
    }


    public static void main(String[] args) {
            /*System.out.println(equalDate("* * *", "2017 12 18"));
            System.out.println(equalDate("2017 * *", "2017 12 18"));
            System.out.println(equalDate("* * 18", "2017 12 18"));
            System.out.println(equalDate("* * 18", "2017 12 15"));
            System.out.println(equalDate("* * 18", "2015 12 18"));
            System.out.println(equalDate("2017 12 18", "2015 12 18"));*/
            /*System.out.println(format(DateUtils.addWeeks(new Date(),1),DATE_FORMAT_PATTERN));
            System.out.println(format(DateUtils.addMonths(new Date(),1),DATE_FORMAT_PATTERN));
            System.out.println(format(DateUtils.addMonths(new Date(),3),DATE_FORMAT_PATTERN));
            System.out.println(format(DateUtils.addYears(new Date(),1),DATE_FORMAT_PATTERN));*/
            log.info("nowStr:{}", DateUtil.format(new Date(),DATE_FORMAT_PATTERN_1));
        }
}
