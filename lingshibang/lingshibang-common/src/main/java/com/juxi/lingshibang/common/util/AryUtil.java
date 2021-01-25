package com.juxi.lingshibang.common.util;

import java.lang.reflect.Array;
import java.util.*;

public class AryUtil {

    /***
     * 将列表转换成数组。如果 value 为 null 时 返回 null , 如果 value 个数为 0 时 返回 null
     * @param value 待转换列表
     * @return 转换后的数组
     */
    public static <T> T[] toArray(List<T> value) {
        if (null == value) return null;
        if (0 == value.size()) return null;
        @SuppressWarnings("unchecked")
        T[] ary = (T[]) Array.newInstance(value.get(0).getClass(), value.size());
        value.toArray(ary);
        return ary;
    }

    /***  将集合强转为数组。注意，集合是无序的，本函数将强制转成有序，慎用 */
    public static <T> T[] toArray(Set<T> value) {
        List<T> list = toList(value);
        return toArray(list);
    }

    /*** 将数组转为列表 */
    public static <T> List<T> toList(T... value) {
        if (null == value) return null;
        return Arrays.asList(value);
    }

    /*** 将集合强转为列表。注意，集合是无序的，本函数将强制转成有序，慎用。  */
    public static <T> List<T> toList(Set<T> value) {
        if (null == value) return null;
        List<T> list = new ArrayList<T>();
        list.addAll(value);
        return list;
    }

    /*** 将数组转为集合 */
    public static <T> Set<T> toSet(T... value) {
        if (null == value) return null;
        Set<T> set = new HashSet<T>();
        set.addAll(Arrays.asList(value));
        return set;
    }

    /*** 将列表转为集合 */
    public static <T> Set<T> toSet(List<T> value) {
        if (null == value) return null;
        Set<T> set = new HashSet<T>();
        set.addAll(value);
        return set;
    }


    /*** 判断元素是否在列表中  */
    public static <T> boolean isContains(T value, List<T> list) {
        return list.contains(value);
    }

    /*** 判断元素是否在集合中  */
    public static <T> boolean isContains(T value, Set<T> set) {
        return set.contains(value);
    }

    /*** 判断元素是否在数组中  */
    public static <T> boolean isContains(T value, T... ary) {
        return isContains(Arrays.asList(ary), value);
    }


    /*** 求和  */
    public static <T extends Number> double Sum(T... value) {
        if (value.length == 0) return 0;
        double d = 0;
        for (T t : value) d = d + t.doubleValue();
        return d;
    }

    /*** 求和  */
    public static <T extends Number> double Sum(List<T> value) {
        if (value.size() == 0) return 0;
        double d = 0;
        for (T t : value) d = d + t.doubleValue();
        return d;
    }

    /*** 求和  */
    public static <T extends Number> double Sum(Set<T> value) {
        if (value.size() == 0) return 0;
        double d = 0;
        for (T t : value) d = d + t.doubleValue();
        return d;
    }

    /*** 求平均  */
    public static <T extends Number> double Avg(T... value) {
        if (value.length == 0) return 0;
        return Sum(value) / value.length;
    }

    /*** 求平均  */
    public static <T extends Number> double Avg(List<T> value) {
        if (value.size() == 0) return 0;
        return Sum(value) / value.size();
    }

    /*** 求平均  */
    public static <T extends Number> double Avg(Set<T> value) {
        if (value.size() == 0) return 0;
        return Sum(value) / value.size();
    }

    /*** 求最大值  */
    public static <T extends Number> double Max(T... value) {
        double max = Double.MIN_VALUE;
        for (T t : value) if (max < t.doubleValue()) max = t.doubleValue();
        return max;
    }

    /*** 求最大值  */
    public static <T extends Number> double Max(List<T> value) {
        double max = Double.MIN_VALUE;
        for (T t : value) if (max < t.doubleValue()) max = t.doubleValue();
        return max;
    }

    /*** 求最大值  */
    public static <T extends Number> double Max(Set<T> value) {
        double max = Double.MIN_VALUE;
        for (T t : value) if (max < t.doubleValue()) max = t.doubleValue();
        return max;
    }

    /*** 求最小值  */
    public static <T extends Number> double Min(T... value) {
        double min = Double.MAX_VALUE;
        for (T t : value) if (min > t.doubleValue()) min = t.doubleValue();
        return min;
    }

    /*** 求最小值  */
    public static <T extends Number> double Min(List<T> value) {
        double min = Double.MAX_VALUE;
        for (T t : value) if (min > t.doubleValue()) min = t.doubleValue();
        return min;
    }

    /*** 求最小值  */
    public static <T extends Number> double Min(Set<T> value) {
        double min = Double.MAX_VALUE;
        for (T t : value) if (min > t.doubleValue()) min = t.doubleValue();
        return min;
    }


    /***  子集	 */
    public static byte[] Subset(byte[] src, int start, int end) {
        byte[] rt = new byte[end - start];
        System.arraycopy(src, start, rt, 0, rt.length);
        return rt;
    }

    /***  子集	 */
    public static <T> T[] Subset(T[] src, int start, int end) {
        @SuppressWarnings("unchecked") T[] rt = (T[]) Array.newInstance(src[0].getClass(), end - start);
        System.arraycopy(src, start, rt, 0, rt.length);
        return rt;
    }

    /***  子集  */
    public static <T> List<T> Subset(List<T> src, int start, int end) {
        List<T> sub = new ArrayList<T>();
        for (int i = start; i < end; i++) sub.add(src.get(i));
        return sub;
    }

    /*** 子集。注意，集合是无序的，本函数将强制转成有序，慎用。*/
    public static <T> Set<T> Subset(Set<T> src, int start, int end) {
        List<T> sub = new ArrayList<T>();
        List<T> pnt = toList(src);
        for (int i = start; i < end; i++) sub.add(pnt.get(i));
        return toSet(pnt);
    }

    /*** 并集 */
    public static <T> T[] Union(T[]... ary) {
        List<T> lists = new ArrayList<T>();
        for (T[] a : ary) lists.addAll(toList(a));
        return toArray(lists);
    }

    /*** 并集 */
    public static <T> List<T> Union(List<T>... list) {
        List<T> lists = new ArrayList<T>();
        for (List<T> l : list) lists.addAll(l);
        return lists;
    }

    /*** 并集 */
    public static <T> Set<T> Union(Set<T>... set) {
        Set<T> sets = new HashSet<T>();
        for (Set<T> s : set) sets.addAll(s);
        return sets;
    }

    /***  交集 */
    public static <T> T[] Intersection(T[] ary, T[]... otherary) {
        List<T> lists = new ArrayList<T>();
        lists.addAll(toList(ary));
        for (T[] a : otherary) lists.retainAll(toList(a));
        return toArray(lists);
    }

    /*** 交集  */
    public static <T> List<T> Intersection(List<T> list, List<T>... otherlist) {
        List<T> lists = new ArrayList<T>();
        lists.addAll(list);
        for (List<T> l : otherlist) lists.retainAll(l);
        return lists;
    }

    /*** 交集 */
    public static <T> Set<T> Intersection(Set<T> set, Set<T>... otherset) {
        Set<T> sets = new HashSet<T>();
        sets.addAll(set);
        for (Set<T> s : otherset) sets.retainAll(s);
        return sets;
    }

    /*** 差集 */
    public static <T> T[] Difference(T[] ary, T[]... otherary) {
        List<T> lists = new ArrayList<T>();
        lists.addAll(toList(ary));
        for (T[] a : otherary) lists.removeAll(toList(a));
        return toArray(lists);
    }

    /**
     * 差集
     */
    public static <T> List<T> Difference(List<T> list, List<T>... otherlist) {
        List<T> lists = new ArrayList<T>();
        lists.addAll(list);
        for (List<T> l : otherlist) lists.removeAll(l);
        return lists;
    }

    /*** 差集 */
    public static <T> Set<T> Difference(Set<T> set, Set<T>... otherset) {
        Set<T> sets = new HashSet<T>();
        sets.addAll(set);
        for (Set<T> s : otherset) sets.removeAll(s);
        return sets;
    }


    /*** 合并 */
    public static <T> String Join(T[] src) {
        StringBuffer sb = new StringBuffer();
        for (T s : src) sb.append(s.toString());
        return sb.toString();
    }

    /*** 合并 */
    public static <T> String Join(String delimiter, T... src) {
        StringBuffer sb = new StringBuffer();
        for (T s : src) sb.append(s.toString() + delimiter);
        String sr = sb.toString();
        return sr.substring(0, sr.length() - delimiter.length());
    }

    /*** 合并 */
    public static <T> String Join(List<T> src) {
        return Join(toArray(src));
    }

    /*** 合并 */
    public static <T> String Join(String delimiter, List<T> src) {
        return Join(delimiter, toArray(src));
    }

    /*** 合并 */
    public static <T> String Join(Set<T> src) {
        return Join(toArray(src));
    }

    /*** 合并 */
    public static <T> String Join(String delimiter, Set<T> src) {
        return Join(delimiter, toArray(src));
    }


}
