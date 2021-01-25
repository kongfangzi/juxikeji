package com.juxi.lingshibang.common.enums;

/**
 * 阿里短信signName（申请通过的signName）
 *
 * @author xiaoxiong
 */
public enum SmsSignNameEnum {

    /**
     * 【登录验证】xxxx....
     */
    LOGIN_VALIDATE("智慧网咖"),
    ;

    private String signName;

    SmsSignNameEnum(String signName) {
        this.signName = signName;
    }

    public String getSignName() {
        return signName;
    }
}
