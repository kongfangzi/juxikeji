package com.juxi.lingshibang.common.enums;

/**
 * 短信模板类型枚举
 *
 * @author yks
 */
public enum SmsTemplateTypeEnum {

    //登录
    login(1, "SMS_186618914"),

    //提现
    withdraw(2, "SMS_186618914"),

    //支付宝绑定
    alipay_bind(3, "SMS_186618914"),

    //支付宝解绑定
    alipay_un_bind(4, "SMS_186618914");

    /**
     * 短信发送类型
     */
    private int type;

    /**
     * 研发短信验证码
     */
    private String templateCode;

    SmsTemplateTypeEnum(int type, String templateCode) {
        this.type = type;
        this.templateCode = templateCode;
    }

    public static SmsTemplateTypeEnum getType(int type) {
        for (SmsTemplateTypeEnum s : SmsTemplateTypeEnum.values()) {
            if (s.type == type) {
                return s;
            }
        }
        throw new IllegalArgumentException("wrong sms template type: " + type);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }
}