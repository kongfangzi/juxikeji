package com.juxi.lingshibang.common.util;

import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @version 1.0
 * @date 2017-03-15
 */
public final class NumberUtil extends NumberUtils {
    private static DecimalFormat DF = new DecimalFormat("###################.###########");

    public static String doubleToStr(double d) {
        return DF.format(d);
    }

    public static double optDouble(Double d) {
        return optDouble(d, 0d);
    }
    public static double optDouble(Double d, double defVal) {
        return d == null ? defVal : d;
    }

    public static float optFloat(Float f) {
        return optFloat(f, 0f);
    }
    public static float optFloat(Float f, float defVal) {
        return f == null ? defVal : f;
    }

    public static byte optByte(Byte b) {
        return optByte(b, (byte)0);
    }
    public static byte optByte(Byte b, byte defVal) {
        return b == null ? defVal : b;
    }

    public static short optShort(Short s) {
        return optShort(s, (short)0);
    }
    public static short optShort(Short s, short defVal) {
        return s == null ? defVal : s;
    }

    public static char optChar(Character c) {
        return optChar(c, (char)0);
    }
    public static char optChar(Character c, char defVal) {
        return c == null ? defVal : c;
    }

    public static int optInt(Integer i) {
        return optInt(i, 0);
    }
    public static int optInt(Integer i, int defVal) {
        return i == null ? defVal : i;
    }

    public static long optLong(Long l) {
        return optLong(l, 0L);
    }
    public static long optLong(Long l, long defVal) {
        return l == null ? defVal : l;
    }

    public static int optLongToInt(Long l) {
        return l == null ? 0 : l.intValue();
    }

    public static boolean optBoolean(Boolean b) {
        return optBoolean(b, false);
    }
    public static boolean optBoolean(Boolean b, boolean defVal) {
        return b == null ? defVal : b;
    }

    public static boolean toBoolean(String b) {
        return StringUtil.equalsIgnoreCase(b, "true") || StringUtil.equals(b, "1");
    }
    public static boolean toBoolean(String b, boolean defVal) {
        if (StringUtil.equalsIgnoreCase(b, "true") || StringUtil.equals(b, "1")) {
            return true;
        } else if (StringUtil.equalsIgnoreCase(b, "false") || StringUtil.equals(b, "0")) {
            return false;
        } else {
            return defVal;
        }
    }

    public static boolean negateBoolean(boolean b) {
        return !b;
    }

    public static Boolean negateBoolean(Boolean b) {
        return BooleanUtils.negate(b);
    }

    public static boolean andBoolean(boolean... arr) {
        return BooleanUtils.and(arr);
    }

    public static boolean orBoolean(boolean... arr) {
        return BooleanUtils.or(arr);
    }

    public static byte[] toBytes(int val) {
        return Ints.toByteArray(val);
    }
    public static byte[] toBytes(long val) {
        return Longs.toByteArray(val);
    }
    public static byte[] toBytes(double val) {
        return toBytes(Double.doubleToRawLongBits(val));
    }

    public static int toInt(byte[] vals) {
        return Ints.fromByteArray(vals);
    }

    public static long toLong(byte[] vals) {
        return Longs.fromByteArray(vals);
    }

    public static double toDouble(byte[] vals) {
        return Double.longBitsToDouble(toLong(vals));
    }

    public static boolean isHexNumber(String value) {
        if (StringUtil.isBlank(value)) {
            return false;
        }
        int index = value.startsWith("-") ? 1 : 0;
        return value.startsWith("0x", index) || value.startsWith("0X", index) ||value.startsWith("#", index);
    }

    public static int toInt32(long val) {
        if ((int) val == val) {
            return (int) val;
        }
        throw new IllegalArgumentException("Int " + val + " out of range.");
    }

    public static Integer toIntObject(String str) {
        if (StringUtil.isBlank(str)) {
            return null;
        }
        return toInt(str);
    }

    public static Boolean toBooleanObject(String str) {
        if (StringUtil.isBlank(str)) {
            return false;
        }
        return toBoolean(str);
    }

    public static Long toLongObject(String str) {
        if (StringUtil.isBlank(str)) {
            return null;
        }
        return toLong(str);
    }

    public static Double toDoubleObject(String str) {
        if (StringUtil.isBlank(str)) {
            return null;
        }
        return toDouble(str);
    }

    public static Float toFloatObject(String str) {
        if (StringUtil.isBlank(str)) {
            return null;
        }
        return toFloat(str);
    }

    public static Integer bigDecimalToInteger(BigDecimal bigDecimal) {
        return bigDecimal == null ? null : bigDecimal.intValue();
    }

    public static int bigDecimalOptInteger(BigDecimal bigDecimal) {
        return bigDecimal == null ? 0 : bigDecimal.intValue();
    }

    public static Long bigDecimalToLong(BigDecimal bigDecimal) {
        return bigDecimal == null ? null : bigDecimal.longValue();
    }

    public static long bigDecimalOptLong(BigDecimal bigDecimal) {
        return bigDecimal == null ? 0L : bigDecimal.longValue();
    }

    public static BigDecimal numToBigDecimal(Integer i) {
        if (i == null) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(i);
    }
    public static BigDecimal numToBigDecimal(Long l) {
        if (l == null) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(l);
    }

    public static <NUM extends Number> boolean isGreaterZero(NUM num) {
        if (num != null) {
            if (num instanceof Byte) {
                return num.byteValue() > 0;
            }
            if (num instanceof Double) {
                return num.doubleValue() > 0d;
            }
            if (num instanceof Float) {
                return num.floatValue() > 0f;
            }
            if (num instanceof Integer) {
                return num.intValue() > 0;
            }
            if (num instanceof Long) {
                return num.longValue() > 0l;
            }
            if (num instanceof Short) {
                return num.shortValue() > 0;
            }
        }
        return false;
    }

    /**
     * 保留2位小数，四舍五入
     * @param num1
     * @param num2
     * @return
     */
    public static BigDecimal  divideBigDecimal(BigDecimal num1,BigDecimal num2){
        if (num1 == null || num2==null) {
            return BigDecimal.ZERO;
        }
        if(BigDecimal.ZERO.equals(num1)){
            return BigDecimal.ZERO;
        }
        return num1.divide(num2,2, RoundingMode.HALF_DOWN);
    }
}
