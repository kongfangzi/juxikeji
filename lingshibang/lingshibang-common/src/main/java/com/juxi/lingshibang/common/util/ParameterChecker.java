package com.juxi.lingshibang.common.util;

import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 参数校验工具类
 * @author yks
 * @date 2020-03-26
 */
@UtilityClass
public class ParameterChecker {


    /**
     * 最新手机号校验
     * @param phone
     * @return
     */
    public boolean checkPhone(String phone) {
        if (!StringUtils.hasText(phone)) {
            return false;
        }
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            return m.matches();
        }
    }


}
