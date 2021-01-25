package com.juxi.lingshibang.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.reflect.TypeToken;
import com.juxi.lingshibang.common.util.json.JSONConvert;
import com.juxi.lingshibang.common.util.json.impl.FastjsonConvert;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @version 1.0
 * @date 2017-03-22
 */
public final class ListUtil extends ListUtils {
    private static JSONConvert JSON_CONVERT = new FastjsonConvert();    // 以fastjson为工具
    private ListUtil() {}

    /**
     * list去除重复
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> listWithoutDuplicate(List<T> list) {
        return listWithoutDuplicateBySort(list, null);
    }

    /**
     * list去除重复，并排序
     * @param list
     * @param comparator
     * @param <T>
     * @return
     */
    public static <T> List<T> listWithoutDuplicateBySort(List<T> list, Comparator<T> comparator) {
        if (list != null && list.size() > 1) {
            List<T> l = Lists.newArrayList(Sets.newHashSet(list));
            if (comparator != null) {
                Collections.sort(l, comparator);
            }
            return l;
        }
        return list;
    }

    public static <F, T> List<T> transform(F[] fromArr, Function<? super F, ? extends T> function) {
        List<T> list = Lists.newArrayList();
        if (ArrayUtil.isNotBlank(fromArr) && function != null) {
            for (F f : fromArr) {
                if (f != null) {
                    T t = function.apply(f);
                    if (t != null) {
                        list.add(t);
                    }
                }
            }
        }
        return list;
    }

    public static <F, T> List<T> transform(List<F> fromList, Function<? super F, ? extends T> function) {
        return transform(fromList, 0, function);
    }
    public static <F, T> List<T> transform(List<F> fromList, int count, Function<? super F, ? extends T> function) {
        List<T> list = Lists.newArrayList();
        if (isNotBlank(fromList) && function != null) {
            for (F f : fromList) {
                if (f != null) {
                    T t = function.apply(f);
                    if (t != null) {
                        list.add(t);
                    }
                    if (count > 0 && list.size() >= count) {
                        break;
                    }
                }
            }
//            fromList.forEach(f -> {
//                if (f != null) {
//                    T t = function.apply(f);
//                    if (t != null) {
//                        list.add(t);
//                    }
//                }
//            });
        }
        return list;
    }

    public static <F, T> List<T> transformJSONArray(JSONArray fromList, Function<? super F, ? extends T> function) {
        List<T> list = Lists.newArrayList();
        if (fromList != null && fromList.size() > 0 && function != null) {
            for (int i = 0; i < fromList.size(); i++) {
                Object o = fromList.get(i);
                if (o != null) {
                    F f = (F) o;
                    T t = function.apply(f);
                    if (t != null) {
                        list.add(t);
                    }
                }
            }
        }
        return list;
    }
    /**
     * 队列长度是否大于size
     * @param list
     * @param size
     * @return
     */
    public static boolean sizeGreaterThan(List list, int size) {
        return list != null && list.size() > size;
    }
    /**
     * 队列长度是否大于或等于size
     * @param list
     * @param size
     * @return
     */
    public static boolean sizeGreaterOrEqualThan(List list, int size) {
        return list != null && list.size() >= size;
    }

    public static boolean isNotBlank(List list) {
        return list != null && list.size() > 0;
    }

    public static boolean isBlank(List list) {
        return !isNotBlank(list);
    }

    public static <T> boolean isNotBlankDo(List<T> list, Consumer<List<T>> c) {
        boolean b = isNotBlank(list);
        if (b && c != null) {
            c.accept(list);
        }
        return b;
    }

    public static <T> boolean hasObject(List<T> list, T t) {
        return isNotBlank(list) && list.contains(t);
    }

    public static <T> T[] toArray(List<T> list) {
        if (isNotBlank(list)) {
            Class clazz = list.get(0).getClass();
            T[] a = (T[])java.lang.reflect.Array.
                    newInstance(clazz, list.size());
            return list.toArray(a);
        }
        return null;
    }

    public static <T> List<T> parseToList(String jsonString) {
        if (!JSONUtil.isJSONArray(jsonString)) {
            return null;
        }
        return JSON_CONVERT.parseJSONStr(jsonString, new TypeToken<ArrayList<T>>() {}.getType());
    }

    public static <T> List<T> parseToList(JSONArray arr) {
        if (JSONUtil.isBlank(arr)) {
            return null;
        }
        return JSON_CONVERT.parseJSONArray(arr, new TypeToken<ArrayList<T>>() {}.getType());
    }

    public static <T> T copy(Object source, Class<T> c) {
        if (source == null) {
            return null;
        }
        try {
            T instance = c.newInstance();
            BeanUtils.copyProperties(source, instance);
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <E, T> List<T> copyList(List<E> sources, Class<T> c) {
        if (CollectionUtils.isEmpty(sources)) {
            return new ArrayList<T>();
        }
        List<T> list = new ArrayList<T>();
        for (E source : sources) {
            list.add(copy(source, c));
        }
        return list;
    }


    public static String toString(List<?> list) {
        if (list == null) {
            return "";
        }
        return JSON.toJSONString(list);
    }


    public  static  List<String> toStringList(String str,String separator){
            if(StringUtil.isEmpty(str)||StringUtil.isEmpty(separator)){
                return null;
            }
            return  Arrays.asList(str.split(separator));
    }

    public  static  List<Long> toLongList(String ids,String separator){
        if(StringUtil.isEmpty(ids)||StringUtil.isEmpty(separator)){
            return null;
        }
        return  Arrays.asList(ids.split(separator)).stream()
                .map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
    }
}
