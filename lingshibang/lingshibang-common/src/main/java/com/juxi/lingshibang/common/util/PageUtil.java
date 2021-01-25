package com.juxi.lingshibang.common.util;

import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页工具类
 * @author yks
 * @date 2020-01-06
 */
@UtilityClass
public class PageUtil {

    /**
     * 获取分页对象集合
     * @param list
     * @param current
     * @param size
     * @return
     */
    public List getPageList(List list, Integer current, Integer size) {
        int total = list.size();
        int pageCount = total / size;
        int fromIndex = size * (current - 1);
        int toIndex = fromIndex + size;
        if (toIndex >= total) {
            toIndex = total;
        }
        if (current > pageCount + 1) {
            fromIndex = 0;
            toIndex = 0;
        }
        return list.subList(fromIndex, toIndex);
    }

    /**
     * 获取分页对象集合
     *
     * @param list
     * @param current
     * @param size
     * @return
     */
    public Map<String, Object> getPageMap(List list, Integer current, Integer size) {
        Map<String, Object> resultMap = new HashMap<>(2);
        long total = list.size();
        resultMap.put("total", total);
        resultMap.put("list", getPageList(list, current, size));
        return resultMap;
    }

}
