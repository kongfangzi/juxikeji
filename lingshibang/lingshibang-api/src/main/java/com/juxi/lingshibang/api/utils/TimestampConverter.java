package com.juxi.lingshibang.api.utils;

import org.springframework.core.convert.converter.Converter;
import java.sql.Timestamp;
import java.util.Date;
/**
 * Author:xiaowei.zhu
 * 2020/1/19 20:27
 */
public class TimestampConverter implements Converter {
    public Timestamp convert(Date date) {
        if(date != null){
            return new Timestamp(date.getTime());
        }
        return null;
    }

    @Override
    public Object convert(Object o) {
        return null;
    }
}
