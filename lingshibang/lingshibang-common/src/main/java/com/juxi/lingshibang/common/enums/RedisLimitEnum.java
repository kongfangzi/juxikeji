package com.juxi.lingshibang.common.enums;

import java.util.concurrent.TimeUnit;

/**
 * redis 次数限制
 * @author yks
 * @date 2019-09-26
 */
public enum RedisLimitEnum {

    /**
     * 一个账号每天只能发送10次短信
     */
    member_login_sms_10_one_day(10L, TimeUnit.SECONDS),
    ;
    /**
     * 短信发送次数
     */
    private Long time;

    private TimeUnit timeUnit;

    RedisLimitEnum(Long time, TimeUnit timeUnit) {
        this.time = time;
        this.timeUnit = timeUnit;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }
}
