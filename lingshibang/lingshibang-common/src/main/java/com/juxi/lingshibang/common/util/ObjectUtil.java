package com.juxi.lingshibang.common.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 对象工具
 * @date 2019-01-16
 */
public final class ObjectUtil {

    public static boolean isNotBlank(Object o) {
        return !isBlank(o);
    }

    public static boolean isBlank(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            return StringUtil.isBlank(o.toString().trim());
        } else if (o instanceof List) {
            return ListUtil.isBlank((List) o);
        } else if (o instanceof Map) {
            return MapUtil.isBlank((Map) o);
        } else if (o instanceof Set) {
            if (((Set) o).size() <= 0) {
                return true;
            }
        } else if (o instanceof Object[]) {
            if (((Object[]) o).length <= 0) {
                return true;
            }
        } else if (o instanceof int[]) {
            if (((int[]) o).length <= 0) {
                return true;
            }
        } else if (o instanceof long[]) {
            if (((long[]) o).length <= 0) {
                return true;
            }
        } else if (o instanceof float[]) {
            if (((float[]) o).length <= 0) {
                return true;
            }
        } else if (o instanceof double[]) {
            if (((double[]) o).length <= 0) {
                return true;
            }
        } else if (o instanceof short[]) {
            if (((short[]) o).length <= 0) {
                return true;
            }
        } else if (o instanceof char[]) {
            if (((char[]) o).length <= 0) {
                return true;
            }
        } else if (o instanceof boolean[]) {
            if (((boolean[]) o).length <= 0) {
                return true;
            }
        }

        return false;
    }

    public static boolean isOneBlank(Object... objs) {
        if (objs != null) {
            for (Object obj : objs) {
                if (isBlank(obj)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isAllBlank(Object... objs) {
        if (objs != null) {
            for (Object obj : objs) {
                if (isNotBlank(obj)) {
                    return false;
                }
            }
        }
        return true;
    }
}
