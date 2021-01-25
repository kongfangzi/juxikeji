package com.juxi.lingshibang.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.juxi.lingshibang.common.util.json.JSONConvert;
import com.juxi.lingshibang.common.util.json.impl.FastjsonConvert;
import org.apache.commons.collections.MapUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Xukai
 * @version 1.0
 * @date 2017-05-09
 */
public final class MapUtil extends MapUtils {
    private static JSONConvert JSON_CONVERT = new FastjsonConvert();    // 以fastjson为工具
    private MapUtil() {}

    public static <K, M> Map<K, M> listToMap(List<M> list, Function<M, K> function) {
        Map<K, M> map = Maps.newHashMap();
        if (ListUtil.isNotBlank(list)) {
            for (M m : list) {
                if (m != null) {
                    K k = function.apply(m);
                    if (k != null) {
                        map.put(k, m);
                    }
                }
            }
        }
        return map;
    }

    public static boolean isNotBlank(Map map) {
        return map != null && map.size() > 0;
    }

    public static boolean isBlank(Map map) {
        return !isNotBlank(map);
    }

    public static <K, M> boolean isNotBlankDo(Map<K, M> map, Consumer<Map<K, M>> c) {
        boolean b = isNotBlank(map);
        if (b && c != null) {
            c.accept(map);
        }
        return b;
    }

    /**
     * json字符串转换成 HashMap
     */
    public static Map<String, Object> parseToMap(String jsonString) {
        if (!JSONUtil.isJSONObject(jsonString)) {
            return null;
        }
        return JSON_CONVERT.parseJSONStr(jsonString, new TypeToken<HashMap<String, Object>>() {}.getType());
    }

    public static Map<String, Object> parseToMap(JSONObject obj) {
        if (JSONUtil.isBlank(obj)) {
            return null;
        }
        return JSON_CONVERT.parseJSONObject(obj, new TypeToken<HashMap<String, Object>>() {}.getType());
    }

    public static <V> Map<String, V> parseToMap(String jsonString, Class<V> vClass) {
        if (!JSONUtil.isJSONObject(jsonString)) {
            return null;
        }
        return JSON_CONVERT.parseJSONStr(jsonString, JSONUtil.type(HashMap.class, String.class, vClass));
    }

    public static <V> Map<String, V> parseToMap(JSONObject obj, Class<V> vClass) {
        if (JSONUtil.isBlank(obj)) {
            return null;
        }
        return JSON_CONVERT.parseJSONObject(obj, JSONUtil.type(HashMap.class, String.class, vClass));
    }

    public static <K, V> Map<K, V> newHashMap(K key, V value) {
        Map<K, V> map = Maps.newHashMap();
        if (key != null && value != null) {
            map.put(key, value);
        }
        return map;
    }

    public static <K, V> Map<K, V> newHashMap(K[] keys, V[] values) {
        Map<K, V> map = Maps.newHashMap();
        if (ArrayUtil.isNotBlank(keys) && ArrayUtil.isNotBlank(values)) {
            for (int i = 0; i < keys.length; i++) {
                K key = keys[i];
                V value = null;
                if (i < values.length) {
                    value = values[i];
                }
                if (value != null) {
                    map.put(key, value);
                }
            }
        }
        return map;
    }

    public static String toString(Map<?, ?> map) {
        if (map == null) {
            return "";
        }
        return JSON.toJSONString(map);
    }
}
