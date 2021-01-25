package com.juxi.lingshibang.common.util.bean;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 全局属性
 * @date 2017-11-27
 */
public class Global {
    private static final class PLACE_HOLDER {
        static final Global INSTANCE = new Global();
    }

    public static Global getInstance() {
        return PLACE_HOLDER.INSTANCE;
    }

    // 一些加载的配置
    private Map<String, Object> properties;
    // 一些运行是生成的全局变量
    private Map<String, Object> variables;

    private Global() {
        properties = Maps.newHashMap();
        variables = Maps.newConcurrentMap();
    }

    public void addAll(Map<String, Object> map) {
        properties.putAll(map);
    }

    public <T> void add(String key, T t) {
        properties.put(key, t);
    }

    public <T> T get(String key) {
        if (properties.containsKey(key)) {
            return (T) properties.get(key);
        }
        return null;
    }

    public <T> void setVariable(String key, T t) {
        variables.put(key, t);
    }

    public <T> T getVariable(String key) {
        if (variables.containsKey(key)) {
            return (T) variables.get(key);
        }
        return null;
    }

    public void removeVariable(String key) {
        if (variables.containsKey(key)) {
            variables.remove(key);
        }
    }
}
