package com.juxi.lingshibang.common.util;

import com.google.common.collect.Lists;
import com.google.common.collect.ObjectArrays;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @version 1.0
 * @date 2017-03-22
 */
public final class ArrayUtil extends ArrayUtils {
    private ArrayUtil() {}

    public static <T> T[] concat(T[] arr1, T[] arr2) {
        if (isBlank(arr1) && isBlank(arr2)) {
            return null;
        } else if (isBlank(arr1) && isNotBlank(arr2)) {
            return arr2;
        } else if (isNotBlank(arr1) && isBlank(arr2)) {
            return arr1;
        } else {
            T[] result = Arrays.copyOf(arr1, arr1.length + arr2.length);
            System.arraycopy(arr2, 0, result, arr1.length, arr2.length);
            return result;
        }
    }

    public static <T> T[] concat(T e, T[] arr) {
        return ObjectArrays.concat(e, arr);
    }
    public static <T> T[] concat(T[] arr, T e) {
        return ObjectArrays.concat(arr, e);
    }

    public static <T> boolean isNotBlank(T[] t) {
        return t != null && t.length > 0;
    }

    public static <T> boolean isBlank(T[] t) {
        return !isNotBlank(t);
    }

    public static <T> boolean isNotBlankDo(T[] t, Consumer<T[]> c) {
        boolean b = isNotBlank(t);
        if (b && c != null) {
            c.accept(t);
        }
        return b;
    }
    public static <T> List<T> toList(T[] arr) {
        if (isNotBlank(arr)) {
            return Lists.newArrayList(Arrays.asList(arr));
        }
        return null;
    }

    public static <T> T[] toArray(List<T> list, Class<T> type) {
        return list.toArray((T[]) Array.newInstance(type, list.size()));
    }

    public static <T> String toString(T[] arr) {
        return Arrays.toString(arr);
    }


    public static void main(String[] args){
        System.out.println(Arrays.asList("1280056246976991234".split(",")));
    }
}
