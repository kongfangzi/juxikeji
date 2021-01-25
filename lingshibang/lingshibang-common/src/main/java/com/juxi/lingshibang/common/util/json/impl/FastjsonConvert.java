package com.juxi.lingshibang.common.util.json.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.juxi.lingshibang.common.util.JSONUtil;
import com.juxi.lingshibang.common.util.StringUtil;
import com.juxi.lingshibang.common.util.json.JSONConvert;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @date 2017-07-12
 */
public class FastjsonConvert implements JSONConvert {


    @Override
    public <T> String toJSONStr(T t) {
        return t == null ? null : (t instanceof JSON ? ((JSON) t).toJSONString() : JSON.toJSONString(t));
    }

    @Override
    public <T> JSONObject toJSON(T t) {
        if (t == null) {
            return null;
        }
        if (t instanceof String && JSONUtil.isJSONObject((String) t)) {
            return (JSONObject) JSONObject.parse((String) t);
        }
        return (JSONObject) JSONObject.parse(toJSONStr(t));
    }

    @Override
    public <T> JSONArray toJSONArray(T t) {
        if (t == null) {
            return null;
        }
        if (t instanceof String && JSONUtil.isJSONArray((String) t)) {
            return (JSONArray) JSONArray.parse((String) t);
        }
        return (JSONArray) JSONArray.parse(toJSONStr(t));
    }

    @Override
    public <T> T parseJSONObject(JSONObject obj, Class<T> tClass) {
        return (JSONUtil.isBlank(obj)) ? null : obj.toJavaObject(tClass);
//        return obj == null ? null : JSONObject.parseObject(obj.toJSONString(), tClass);
    }

    @Override
    public <T> T parseJSONObject(JSONObject obj, Type objectType) {
        return (JSONUtil.isBlank(obj)) ? null : obj.toJavaObject(objectType);
//        return (JSONUtil.isBlank(obj)) ? null : JSONObject.parseObject(obj.toJSONString(), objectType);
    }

    @Override
    public <T> T parseJSONStr(String str, Class<T> tClass) {
        return (StringUtil.isBlank(str) || !JSONUtil.isJSON(str)) ? null : JSON.parseObject(str, tClass);
    }

    @Override
    public <T> T parseJSONStr(String str, Type objectType) {
        return (StringUtil.isBlank(str) || !JSONUtil.isJSON(str)) ? null : JSON.parseObject(str, objectType);
    }

    @Override
    public <T> List<T> parseJSONArray(String str, Class<T> mClass) {
        if (StringUtil.isBlank(str) || !JSONUtil.isJSONArray(str)) {
            return null;
        }
        return parseJSONStr(str, JSONUtil.type(List.class, mClass));
    }

    @Override
    public <T> List<T> parseJSONArray(String str, Type objectType) {
        if (StringUtil.isBlank(str) || !JSONUtil.isJSONArray(str)) {
            return null;
        }
        return JSONArray.parseObject(str, objectType);
    }

    @Override
    public <T> List<T> parseJSONArray(JSONArray arr, Type objectType) {
//        return (JSONUtil.isBlank(arr)) ? null : arr.toJavaObject(objectType);
        return (JSONUtil.isBlank(arr)) ? null : JSONArray.parseObject(arr.toJSONString(), objectType);
    }

    @Override
    public <T> List<T> parseJSONArray(JSONArray arr, Class<T> mClass) {
           if(JSONUtil.isBlank(arr)){
               return null;
           }
           return parseJSONStr(arr.toJSONString(), JSONUtil.type(List.class, mClass));
    }
}
