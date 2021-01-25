package com.juxi.lingshibang.common.util.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @date 2017-07-12
 */
public interface JSONConvert {

    /**
     * 将对象转化成json字符串
     *
     * @param t
     * @param <T>
     * @return
     */
    public <T> String toJSONStr(T t);

    /**
     * 将对象转化成JSON对象
     *
     * @param t
     * @param <T>
     * @return
     */
    public <T> JSONObject toJSON(T t);

    /**
     * 将对象转化成JSON数组
     * @param t
     * @param <T>
     * @return
     */
    public <T> JSONArray toJSONArray(T t);

    /**
     * 解析json对象为对象
     * @param obj
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T parseJSONObject(JSONObject obj, Class<T> tClass);

    /**
     * 解析json对象为对象
     * @param obj
     * @param objectType
     * @param <T>
     * @return
     */
    public <T> T parseJSONObject(JSONObject obj, Type objectType);

    /**
     * 解析json字符串为对象
     * @param str
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T parseJSONStr(String str, Class<T> tClass);

    /**
     * 解析json字符串为对象
     * @param str
     * @param objectType
     * @param <T>
     * @return
     */
    public <T> T parseJSONStr(String str, Type objectType);

    /**
     * 解析json数组字符串转换为list
     * @param str
     * @param mClass
     * @param <T>
     * @return
     */
    public <T> List<T> parseJSONArray(String str, Class<T> mClass);

    /**
     * 解析json数组字符串转换为list
     * @param str
     * @param objectType
     * @param <T>
     * @return
     */
    public <T> List<T> parseJSONArray(String str, Type objectType);

    /**
     * 解析json数组字符串转换为list
     * @param arr
     * @param objectType
     * @param <T>
     * @return
     */
    public <T> List<T> parseJSONArray(JSONArray arr, Type objectType);

    /**
     * 解析json数组字符串转换为list
     * @param arr
     * @param mClass
     * @param <T>
     * @return
     */
    public <T> List<T> parseJSONArray(JSONArray arr, Class<T> mClass);

}
