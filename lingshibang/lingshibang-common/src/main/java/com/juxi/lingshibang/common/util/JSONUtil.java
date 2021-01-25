package com.juxi.lingshibang.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @version 1.0
 * @date 2017-05-02
 */
@Slf4j
public final class JSONUtil {

    /**
     * 简单判断是否是JSON
     * @param str
     * @return
     */
    public static boolean isJSON(String str) {
        return isJSONObject(str) || isJSONArray(str);
    }

    /**
     * 简单判断是否是jsonobject
     * 仅判断是否以 { 开头 } 结尾
     *
     * @param str
     * @return
     */
    public static boolean isJSONObject(String str) {
        return StringUtil.startsWith(str, "{") && StringUtil.endsWith(str, "}");
    }

    /**
     * 简单判断是否是jsonarray
     * 仅判断是否以 [ 开头 ]  结尾
     *
     * @param str
     * @return
     */
    public static boolean isJSONArray(String str) {
        return StringUtil.startsWith(str, "[") && StringUtil.endsWith(str, "]");
    }

    public static boolean isBlank(JSONObject obj) {
        return obj == null || obj.size() <= 0;
    }

    public static boolean isNotBlank(JSONObject obj) {
        return !isBlank(obj);
    }

    public static boolean isBlank(JSONArray arr) {
        return arr == null || arr.size() <= 0;
    }

    public static boolean isNotBlank(JSONArray arr) {
        return !isBlank(arr);
    }

    public static String optStr(JSONObject obj, String key, String defVal) {
        if (isNotBlank(obj)) {
            if (obj.containsKey(key)) {
                try {
                    return obj.getString(key);
                } catch (Exception e) {
                }
            }
        }
        return defVal;
    }

    public static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }

    public static <T> List<T> getList(JSONObject obj, String key, Class<T> tClass) {
        List<T> list = obj.getObject(key, type(List.class, tClass));
        return list;
    }

    /**
     * 暴力解析:Alibaba fastjson
     * @param jsonStr
     * @return
     */
    public final static boolean isJSONValid(String jsonStr) {
        try {
            JSONObject.parseObject(jsonStr);
        } catch (JSONException ex) {
            try {
                JSONObject.parseArray(jsonStr);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }


    public  static  JSONObject  parseEnObject(String enJsonStr) {
        try {
            if (StringUtil.isNotBlank(enJsonStr)) {
                return  JSONObject.parseObject(java.net.URLDecoder.decode(enJsonStr, StandardCharsets.UTF_8.toString()));
            }
            return null;
        } catch (UnsupportedEncodingException e) {
            log.error("errMsg:{}", e);
            return null;
        }
    }

    public  static  JSONArray  parseEnArray(String enJsonStr) {
        try {
            if (StringUtil.isNotBlank(enJsonStr)) {
                return  JSONObject.parseArray(java.net.URLDecoder.decode(enJsonStr, StandardCharsets.UTF_8.toString()));
            }
            return null;
        } catch (UnsupportedEncodingException e) {
            log.error("errMsg:{}", e);
            return null;
        }
    }

    public static void main(String[] args) {
        try {
             //String jsonStrP = "%5B%7B%22hide%22%3Atrue%2C%22must%22%3Atrue%2C%22label%22%3A%22%E8%B4%A7%E5%93%81%E5%90%8D%E7%A7%B0%22%2C%22width%22%3A5%2C%22forbid%22%3Afalse%2C%22repeat%22%3Atrue%2C%22select%22%3Atrue%2C%22fieldId%22%3A%22input_123%22%2C%22readOnly%22%3Afalse%2C%22fieldName%22%3A%22productName%22%2C%22fieldType%22%3A0%2C%22formTypes%22%3A%22textField%22%2C%22defaultValue%22%3A%22%E8%B4%A7%E5%93%81%22%7D%2C%7B%22hide%22%3Atrue%2C%22must%22%3Atrue%2C%22label%22%3A%22%E8%B4%A7%E5%93%81%E6%95%B0%E9%87%8F%22%2C%22width%22%3A5%2C%22forbid%22%3Afalse%2C%22repeat%22%3Atrue%2C%22select%22%3Atrue%2C%22fieldId%22%3A%22input_124%22%2C%22readOnly%22%3Afalse%2C%22fieldName%22%3A%22productNumber%22%2C%22fieldType%22%3A0%2C%22formTypes%22%3A%22numberField%22%2C%22defaultValue%22%3A%220%22%7D%2C%7B%22hide%22%3Atrue%2C%22must%22%3Atrue%2C%22label%22%3A%22%E8%B4%A7%E5%93%81%E4%BB%B7%E6%A0%BC%22%2C%22width%22%3A5%2C%22forbid%22%3Afalse%2C%22repeat%22%3Atrue%2C%22select%22%3Atrue%2C%22fieldId%22%3A%22input_125%22%2C%22readOnly%22%3Afalse%2C%22fieldName%22%3A%22productPrice%22%2C%22fieldType%22%3A0%2C%22formTypes%22%3A%22numberField%22%2C%22defaultValue%22%3A%220.00%22%7D%5D";
             String jsonStrP="%5B%7B%22fieldId%22%3A%22input_1589944691200%22%2C%22label%22%3A%22%E5%8D%95%E8%A1%8C%E6%96%87%E6%9C%AC%22%2C%22formTypes%22%3A%22input%22%2C%22fieldType%22%3A1%7D%5D";
             String jsonStr =java.net.URLDecoder.decode(jsonStrP, "UTF-8");
             log.info(jsonStr);
             JSONArray jsonArray=JSONObject.parseArray(jsonStr);
             log.info(jsonArray.toJSONString());
             log.info(StandardCharsets.UTF_8.name());
        }catch (UnsupportedEncodingException e) {
                e.printStackTrace();
        }

    }
}
