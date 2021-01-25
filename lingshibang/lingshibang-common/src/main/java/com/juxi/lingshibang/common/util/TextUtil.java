package com.juxi.lingshibang.common.util;

import com.juxi.lingshibang.common.util.text.CommonTextValidateRegex;

import java.util.regex.Pattern;

/**
 * @date 2018-01-13
 */
public final class TextUtil {
    public static boolean isMatch(Pattern pattern, String input) {
        return StringUtil.isNotBlank(input) && pattern.matcher(input).matches();
    }

    /**
     * 身份证号脱敏 隐藏生日
     * @param idCard
     * @return
     */
    public static String starIdCard(String idCard) {
        if (StringUtil.isNotBlank(idCard)) {
            if (IdCardUtil.isIdCard18(idCard)) {
                return idCard.replaceAll("(\\d{6})\\d{8}(\\w{4})", "$1********$2");
            } else if (IdCardUtil.isIdCard15(idCard)) {
                return idCard.replaceAll("(\\d{6})\\d{6}(\\w{3})", "$1******$2");
            }
        }
        return idCard;
    }

    /**
     * 姓名脱敏 保留第一个和最后一个字，如果是两个字，保留第一个
     * @param name
     * @return
     */
    public static String starName(String name) {
        if (StringUtil.isNotBlank(name) && name.length() > 1) {
            if (name.length() == 2) {
                name = name.replaceFirst(name.substring(1), "*");
            } else {
//                name = name.replaceFirst(name.substring(1, name.length() - 1), "*");
                // 转义正则表达式的关键字符
                String rep = escapeExprSpecialWord(name.substring(1, name.length() - 1));
                name = name.replaceFirst(rep, "*");
            }
        }
        return name;
    }

    /**
     * 手机号脱敏 隐藏中间4位
     * @param phone
     * @return
     */
    public static String starTelephone(String phone) {
        if (StringUtil.isNotBlank(phone) && isMatch(CommonTextValidateRegex.PATTERN_REGEX_MOBILE_SIMPLE, phone)) {
            return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");

        }
        return phone;
    }

    /**
     * 转义正则特殊字符 （$()*+.[]?\^{},|）
     *
     * @param keyword
     * @return
     */
    public static String escapeExprSpecialWord(String keyword) {
        if (StringUtil.isNotBlank(keyword)) {
            String[] fbsArr = { "\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" };
            for (String key : fbsArr) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }
        return keyword;
    }
}
