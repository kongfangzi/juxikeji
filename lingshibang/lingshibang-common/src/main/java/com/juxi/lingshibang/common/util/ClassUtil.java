package com.juxi.lingshibang.common.util;

import com.google.common.collect.Maps;
import com.juxi.lingshibang.common.util.bean.KeepNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author Xukai
 * @version 1.0
 * @date 2017-05-19
 */
public final class ClassUtil {
    /**
     * 初始化类，数字为0，字符串为"" ，其他为null
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T newObjectWithDefault(Class<T> tClass) {
        try {
            T t = tClass.newInstance();
            Field[] fields = getDeclaredFields(tClass);
            if (ArrayUtil.isNotBlank(fields)) {
                for (Field field : fields) {
                    Type type = field.getType();
                    if (type == String.class) {
                        setFieldValue(field, t, "");
                    } else if (type == BigDecimal.class || isNumberType(type.getClass())) {
                        setFieldValue(field, t, 0);
                    } else if (type == Date.class) {
                        setFieldValue(field, t, new Date());
                    }
                }
            }
            return t;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean isNumberType(Class<?> targetClazz) {
        // 判断包装类
        if (Number.class.isAssignableFrom(targetClazz)) {
            return true;
        }
        // 判断原始类,过滤掉特殊的基本类型
        if (targetClazz == boolean.class || targetClazz == char.class || targetClazz == void.class) {
            return false;
        }
        return targetClazz.isPrimitive();
    }

    public static <T, M> T getDeclaredFieldValue(M obj, Field field, T defVal) {
        if (obj != null) {
            try {
                if (field != null) {
                    boolean b = field.isAccessible();
                    field.setAccessible(true);
                    T t = (T) field.get(obj);
                    field.setAccessible(b);
                    return  t;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return defVal;
    }

    public static <T, M> T getDeclaredFieldValue(M obj, String fieldName, T defVal) {
        if (obj != null) {
            try {
                Class clazz = obj.getClass();
                if (clazz == Class.class) {
                    clazz = (Class) obj;
                }
                Field field = getDeclaredField(clazz, fieldName);
                if (field != null) {
                    return getDeclaredFieldValue(obj, field, defVal);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return defVal;
    }
    public static Field getDeclaredField(Class clazz, String fieldName) {
        for (Class c = clazz; c != Object.class; c = c.getSuperclass()) {
            try {
                return c.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
            }
        }
        return null;
    }
    public static Field[] getDeclaredFields(Class clazz) {
        Field[] fields = null;
        for (Class c = clazz; c != Object.class; c = c.getSuperclass()) {
            fields = ArrayUtil.concat(fields, c.getDeclaredFields());
        }
        return fields;
    }

    public static Map<String, Field> getDeclaredFieldsMap(Class clazz) {
        Field[] fields = getDeclaredFields(clazz);
        Map<String, Field> map = Maps.newConcurrentMap();
        if (ArrayUtil.isNotBlank(fields)) {
            for (Field field : fields) {
                map.put(field.getName(), field);
            }
        }
        return map;
    }

    // 获取当前所在方法
    public static Method getCurrentMethod() {
        StackTraceElement[] yste = Thread.currentThread().getStackTrace();
        if (yste.length < 2) {
            return null;
        }
        /**getMethodName**/
        String str = "";
        for (int i = 0; i < yste.length; i++) {
            if (yste[i].getMethodName().equals("getCurrentMethod")) {
                Class<?> cC = null;
                try {
                    cC = Class.forName(yste[i + 1].getClassName());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (cC == null) {
                    continue;
                }
                Method[] ym = cC.getMethods();
                str = yste[i + 1].toString();
                str = str.substring(0, str.lastIndexOf('('));

                for (int j = 0; j < ym.length; j++) {
                    if (str.endsWith(ym[j].getName())) {
                        return ym[j];
                    }
                }
            }
        }
        return null;
    }

    public static Method getDeclaredMethod(Class fromClass, String methodName, Class<?>... parameterTypes) {
        for (Class c = fromClass; c != Object.class; c = c.getSuperclass()) {
            try {
                return c.getDeclaredMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Method[] getDeclaredMethods(Class clazz) {
        Method[] methods = null;
        for (Class c = clazz; c != Object.class; c = c.getSuperclass()) {
            methods = ArrayUtil.concat(methods, c.getDeclaredMethods());
        }
        return methods;
    }

    public static Object changeDataType(String data, Class<?> clazz) {
        if(clazz == int.class || clazz == Integer.class) {
            return NumberUtil.toInt(data, 0);
        } else if(clazz == long.class || clazz == Long.class) {
            return NumberUtil.toLong(data, 0L);
        } else if(clazz == boolean.class || clazz == Boolean.class) {
            return NumberUtil.toBoolean(data, false);
        } else if(clazz == float.class || clazz == Float.class) {
            return NumberUtil.toFloat(data, 0f);
        } else if(clazz == double.class || clazz == Double.class) {
            return NumberUtil.toDouble(data, 0d);
        } else if(clazz == byte.class || clazz == Byte.class) {
            return NumberUtil.toByte(data, (byte)0);
        } else if(clazz == short.class || clazz == Short.class) {
            return NumberUtil.toShort(data, (short)0);
        } else if(clazz == char.class || clazz == Character.class) {
            return (char) NumberUtil.toInt(data, 0);
        }
        return data;
    }

    public static Object changeDataTypeNull(String data, Class<?> clazz) {
        if (StringUtil.isBlank(data) && (clazz != String.class)) {
            return null;
        }
        return changeDataType(data, clazz);
    }

    private static boolean isBasic(Class clazz) {
        if(clazz == int.class || clazz == long.class || clazz == boolean.class
                || clazz == float.class || clazz == double.class || clazz == short.class
                || clazz == byte.class || clazz == char.class) {
            return true;
        }
        return false;
    }
    public static void setFieldValue(Field field, Object obj, Object val) throws IllegalAccessException {
        setFieldValue(field, obj, val, false);
    }
    public static void setFieldValue(Field field, Object obj, Object val, boolean keepNull) throws IllegalAccessException {
        if (field == null) {
            return;
        }
        if (val == null) {
            if (!keepNull || isBasic(field.getType())) {
                setFieldNullValue(field, obj);
            }
            return ;
        }
        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        Class clazz = field.getType();
        if (val instanceof String) {
            field.set(obj, changeDataType((String) val, clazz));
        } else {
            if (field.getType() == BigDecimal.class) {
                field.set(obj, BigDecimal.valueOf((int)val));
            } else {
                field.set(obj, val);
            }
        }
        field.setAccessible(accessible);
    }

    public static void setFieldNullValue(Field field, Object obj) throws IllegalAccessException {
        if (field == null) {
            return;
        }
        boolean isKeepNull = false;
        KeepNull keepNull = field.getAnnotation(KeepNull.class);
        if (keepNull != null) {
            isKeepNull = true;
        }
        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        Class clazz = field.getType();
        if(clazz == int.class || (!isKeepNull && clazz == Integer.class)) {
            field.set(obj, 0);
        } else if(clazz == long.class || (!isKeepNull && clazz == Long.class)) {
            field.set(obj, 0L);
        } else if(clazz == boolean.class || (!isKeepNull && clazz == Boolean.class)) {
            field.set(obj, false);
        } else if(clazz == float.class || (!isKeepNull && clazz == Float.class)) {
            field.set(obj, 0f);
        } else if(clazz == double.class || (!isKeepNull && clazz == Double.class)) {
            field.set(obj, 0d);
        } else {
            field.set(obj, null);
        }
        field.setAccessible(accessible);
    }


}
