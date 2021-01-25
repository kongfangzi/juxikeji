package com.juxi.lingshibang.api.utils;

import com.juxi.lingshibang.common.utils.DateUtil;
import com.juxi.lingshibang.api.constant.DataTypeConstant;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Jay.Jia
 */
public class XDataUtil {
    private final static int dayLength = 31;

    public static List<String> getXData(Integer dateType){
        List<String> xData = null;
        if (DataTypeConstant.day == dateType){
            xData = getDayXData();
        }else if(DataTypeConstant.week == dateType){
            //一周前的时间
            String upWeekDate = DateUtil.formatDate(DateUtil.addDateWeeks(new Date(), -1), DateUtil.DATE_PATTERN);
            xData = getOtherXData(upWeekDate);
            Collections.reverse(xData);
        }else if(DataTypeConstant.month == dateType){
            //一月前的时间
            String upMongthDate = DateUtil.formatDate(DateUtil.addDateMonths(new Date(), -1), DateUtil.DATE_PATTERN);
            xData = getOtherXData(upMongthDate);
            Collections.reverse(xData);
        }else if(DataTypeConstant.year == dateType){
            //一年前的时间
            String upYearDate = DateUtil.formatDate(DateUtil.addDateYears(new Date(), -1), DateUtil.DATE_PATTERN_MONGTH);
            xData = getYearXData(upYearDate);
            Collections.reverse(xData);
        }
        return xData;
    }

    /**
     * 日数据X轴
     * @return
     */
    private static List<String> getDayXData(){
//        List<String> xData = new ArrayList<>(Arrays.asList("00","01","02","03","04", "05", "06", "07", "08", "09", "10"
//                , "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"));
        List<String> xData = new ArrayList<>(Arrays.asList("10"
                , "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"));
        return xData;
    }

    /**
     * 年数据X轴
     * @return
     */
    private static List<String> getYearXData(String upDate){
        List<String> xData = new ArrayList<>();
        for (int i = 0; i < dayLength; i--){
            String historyDate = DateUtil.formatDate(DateUtil.addDateMonths(new Date(), i), DateUtil.DATE_PATTERN_MONGTH);
            if (upDate.equalsIgnoreCase(historyDate)){
                break;
            }
            xData.add(historyDate);
        }
        return xData;
    }

    /**
     * 其他数据X轴
     * @return
     */
    private static List<String> getOtherXData(String upDate){
        List<String> xData = new ArrayList<>();
        for (int i = 0; i < dayLength; i--){
            String historyDate = DateUtil.formatDate(DateUtil.addDateDays(new Date(), i), DateUtil.DATE_PATTERN);
            if (upDate.equalsIgnoreCase(historyDate)){
                break;
            }
            xData.add(historyDate);
        }
        return xData;
    }

    public static List<String> doXData(List<String> xData, Integer dateType){
        List<String> x = new ArrayList<>();
        if(DataTypeConstant.day == dateType){
            x = xData;
        }else if(DataTypeConstant.week == dateType){
            x = xData.stream().map(d -> d.substring(5)).collect(Collectors.toList());
        }else if(DataTypeConstant.month == dateType){
            x = xData.stream().map(d -> d.substring(5)).collect(Collectors.toList());
        }else if(DataTypeConstant.year == dateType){
            x = xData.stream().map(d -> d.substring(2)).collect(Collectors.toList());
        }
        return x;
    }

}
