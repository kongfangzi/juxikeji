package com.juxi.lingshibang.common.util;

import com.google.common.base.Charsets;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * @version 1.0
 * @date 2017-03-22
 */
public final class StringUtil extends StringUtils {
    private StringUtil() {}

    public static <T> String valueOf(T t, T defVal) {
        if (t == null) {
            return String.valueOf(defVal);
        }
        return String.valueOf(t);
    }

    public static String getQueryString(String str) {
        if (isNotBlank(str)) {
            if (!startsWith(str, "^^")) {
                str = "%" + str + "%";
            } else {
                str = substring(str, 2);
            }
        } else {
            str = null;
        }
        return str;
    }

    public static String joinWithComma(String... strs) {
        return join(strs, ",");
    }
    public static String[] splitWithComma(String str) {
        return split(str, ",");
    }
    public static String[] splitWithUnderline(String str) {
        return split(str, "_");
    }

    public static String joinWithSharp(String... strs) {
        return join(strs, "#");
    }

    public static String joinWithSpace(String... strs) {
        return join(strs, ' ');
    }

    public static String joinWithLine(String... strs) {
        return join(strs, '-');
    }

    public static <T, R> String joinWithComma(List<T> list, Function<T, R> f) {
        StringBuilder sb = new StringBuilder();
        if (ListUtil.isNotBlank(list) && f != null) {
            for (T t : list) {
                if (t != null) {
                    R r = f.apply(t);
                    if (r != null) {
                        if (sb.length() > 0) {
                            sb.append(",");
                        }
                        sb.append(r);
                    }
                }
            }
        }
        return sb.toString();
    }

    public static int compareTo(String str1, String str2) {
        if (isNotBlank(str1)) {
            if (isNotBlank(str2)) {
                return str1.compareTo(str2);
            } else {
                return 1;
            }
        } else {
            if (isNotBlank(str2)) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public static boolean isNotBlankDo(String str, Consumer<String> c) {
        boolean b = isNotBlank(str);
        if (b && c != null) {
            c.accept(str);
        }
        return b;
    }

    public static byte[] getBytesUTF8(String str) {
        if (isBlank(str)) {
            return null;
        }
        return str.getBytes(Charsets.UTF_8);
    }

    public static String bytesToStringUTF8(byte[] bytes) {
        if (bytes != null && bytes.length > 0) {
            return toEncodedString(bytes, Charsets.UTF_8);
        }
        return null;
    }

    public static byte[] getBytes(String str, String charsetName) {
        try {
            if (isNotBlank(str)) {
                return str.getBytes(charsetName);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String replaceFirst(String s, char sub, char with) {
        if (isBlank(s)) {
            return s;
        }
        int index = s.indexOf(sub);
        if (index == -1) {
            return s;
        }
        char[] str = s.toCharArray();
        str[index] = with;
        return new String(str);
    }

    public static String replaceLast(String s, char sub, char with) {
        if (isBlank(s)) {
            return s;
        }

        int index = s.lastIndexOf(sub);
        if (index == -1) {
            return s;
        }
        char[] str = s.toCharArray();
        str[index] = with;
        return new String(str);
    }

    /**
     * 首字母变小写
     */
    public static String firstCharToLowerCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'A' && firstChar <= 'Z') {
            char[] arr = str.toCharArray();
            arr[0] += ('a' - 'A');
            return new String(arr);
        }
        return str;
    }

    /**
     * 首字母变大写
     */
    public static String firstCharToUpperCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'a' && firstChar <= 'z') {
            char[] arr = str.toCharArray();
            arr[0] -= ('a' - 'A');
            return new String(arr);
        }
        return str;
    }

    public static String toCamelCase(String stringWithUnderline) {
        if (stringWithUnderline.indexOf('_') == -1) {
            return stringWithUnderline;
        }

        stringWithUnderline = stringWithUnderline.toLowerCase();
        char[] fromArray = stringWithUnderline.toCharArray();
        char[] toArray = new char[fromArray.length];
        int j = 0;
        for (int i=0; i<fromArray.length; i++) {
            if (fromArray[i] == '_') {
                // 当前字符为下划线时，将指针后移一位，将紧随下划线后面一个字符转成大写并存放
                i++;
                if (i < fromArray.length) {
                    toArray[j++] = Character.toUpperCase(fromArray[i]);
                }
            }
            else {
                toArray[j++] = fromArray[i];
            }
        }
        return new String(toArray, 0, j);
    }

    public static String nvl(String str) {
        return nvl(str, "");
    }
    public static String nvl(String str, String defVal) {
        return isBlank(str) ? defVal : str;
    }

    /**
     * 清除字符串中所有的空格 ,传入null返回null
     *
     * @author wangp
     * @since 2009.02.06
     * @param str
     * @return
     */
    public static String clear(String str) {
        return clear(str, " ");
    }
    /**
     * 清除str中出现的所有str2字符序列 直到结果中再也找不出str2为止 str2 == null时 返回str
     *
     * @author wangp
     * @since 2009.02.06
     * @param str
     *            原始字符串
     * @param str2
     *            清除的目标
     * @return
     */
    public static String clear(String str, String str2) {
        if (str == null)
            return str;
        if (str2 == null)
            return str;
        String reg = "(" + str2 + ")+";
        Pattern p = Pattern.compile(reg);
        while (p.matcher(str).find()) {
            str = str.replaceAll(reg, "");
        }
        return str;
    }

    /**
     * 如果str的长度超过了c则取c-sub.length长度,然后拼上sub结尾
     *
     * @author wangp
     * @since 2009.06.11
     * @param str
     * @param c
     * @param sub
     * @return
     */
    public static String indent(String str, int c, String sub) {
        if (isEmpty(str))
            return str;
        if (str.length() <= c)
            return str;
        sub = nvl(sub);
        c = c - sub.length();
        c = c > str.length() ? 0 : c;
        str = str.substring(0, c);
        return str + sub;
    }

    /**
     * 如果str的长度超过了length,取前length位然后拼上...
     *
     * @author yimian
     * @since 2009.06.11
     * @param str
     * @param length
     * @return
     */
    public static String indent(String str, int length) {
        return indent(str, length, "…");
    }

    /**
     * 创建StringBuilder对象
     */
    public static StringBuilder buildStringBuilder(String... strs) {
        final StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }
        return sb;
    }
}
