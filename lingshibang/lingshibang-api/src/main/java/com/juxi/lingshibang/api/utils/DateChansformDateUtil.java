package com.juxi.lingshibang.api.utils;

import com.juxi.lingshibang.common.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Author:xiaowei.zhu
 * 2020/1/8 11:07
 */
public class DateChansformDateUtil {
    /**
     * 获取当天的时间
     * @param min
     * @param max
     * @return
     */
    public static List<String> getDayData(Integer min,Integer max){
        List<String> dates = new ArrayList<>();
        if(min==null||max==null||(min>max)||min<1||max>23){
            return dates;
        }
        String date;
        for(int i=min;i<max;i++){
            if(i<10){
                date = "0"+i;
            }else{
                date = ""+i;
            }
            dates.add(date);
        }
        return dates;
    }

    /**
     * 获取周的时间(当天往前推6天)
     * @return
     */
    public static List<String> getWeekData(){
        Date now = new Date();
        List<String> dates = new ArrayList<>();

        SimpleDateFormat df = new SimpleDateFormat("E");
        for(int i=6;i>0;i--){
            dates.add(df.format(DateUtil.addDateDays(now,-i)));
        }
        dates.add(DateUtil.formatDate(now,"E"));
        return dates;
    }

    /**
     * 获取周的日期(当天往前推6天)
     * @return
     */
    public static List<String> getWeekDate(){
        Date now = new Date();
        List<String> dates = new ArrayList<>();

        SimpleDateFormat df = new SimpleDateFormat("MM-dd");
        for(int i=6;i>0;i--){
            dates.add(df.format(DateUtil.addDateDays(now,-i)));
        }
        dates.add(df.format(now));
        return dates;
    }

    /**
     * 获取月的时间(当天往前推一个月)
     * @return
     */
    public static List<String> getMonthData(){
        Date lastDay = new Date();
        Date firstDay = DateUtil.addDateDays(DateUtil.addDateMonths(lastDay,-1),1);
        List<String> dates = new ArrayList<>();
        int i=0;
        Date date;
        SimpleDateFormat df = new SimpleDateFormat("MM-dd");
        do{
            date = DateUtil.addDateDays(firstDay, i++);
            dates.add(df.format(date));
        }
        while (date.compareTo(lastDay)<0);
        return dates;
    }


    /**
     * 获取年的时间(当天往前推11个月)
     * @return
     */
    public static List<String> getYearData(){
        Date now = new Date();
        List<String> dates = new ArrayList<>();
        Calendar instance = Calendar.getInstance();
        int month = instance.get(Calendar.MONTH)+1;
        int year = instance.get(Calendar.YEAR);
        int curMon=month;
        int curYear=year;
        for(int i=11;i>-1;i--){
            curMon = month-i;
            if(curMon==0){
                curMon=12;
                curYear = year-1;
            }else if(curMon<0){
                curMon=curMon+12;
                curYear = year-1;
            }else {
                curYear = year;
            }
            dates.add(curYear+(curMon<10?"-0":"-")+(curMon));
        }
        return dates;
    }

    public static void main(String[] args) {
        System.out.println(getWeekData());
        System.out.println(getWeekDate());
        System.out.println(getMonthData());
        System.out.println(getYearData());

    }
}
