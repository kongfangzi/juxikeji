package com.juxi.lingshibang.common.enums;

/**
 * 短信模板code（在阿里云申请的短信模板）
 *
 * @author xiaoxiong
 */
public enum SmsTemplateEnum {

    /**
     * 验证码模板：
     * 尊敬的会员，短信验证码为： ${code}，该验证码5分钟有效，如非本人操作，请忽略此短信。
     */
    VERIFY_CODE("SMS_158345096"),
    /**
     * 交班模板：
     * 尊敬的${shopName},吧员 ${currentNetbarUserName} 已成功交班. 总收入：${sum}元； 网费收入：${netSum}元(现金${netCash}元，线上${netOnline}元)； 水吧收入: ${waterbarSum}元(现金 ${waterbarCash}元，线上${waterbarOnline}元)； 水吧卡扣：${waterbarNetbarpay}元； 赠送卡券：${waterbarCardcnt}张； 赠送卡券总金额：${waterbarCardsum}元； 交班时间：${dutyEndTime} ； 接班人：${nextNetBarUserName}.
     */
    DUTY_CODE("SMS_158445551"),
    /**
     * 卡券赠送通知模板：
     * 亲爱的会员，您收到来自 "${shopInfoName}" 赠送的 "${cardsName}" 卡券，请尽快到店使用。
     */
    CARD_TICKET_CODE("SMS_158440684"),
    /**
     * 生日模板：
     * 亲爱的${memberName}，在今天这个特殊的日子里 ${shopName} 真诚的祝您生日快乐[蛋糕][蛋糕][蛋糕][蛋糕]！同时也送您一件小礼物，您的${cardName}券已经发放至您的会员账户里，可直接到店使用。
     */
    BIRTHDAY_CODE("SMS_158441102"),
    ;

    private String templateCode;

    SmsTemplateEnum(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTemplateCode() {
        return templateCode;
    }
}
