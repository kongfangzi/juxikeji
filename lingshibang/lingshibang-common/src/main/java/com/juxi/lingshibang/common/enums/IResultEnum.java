package com.juxi.lingshibang.common.enums;

/**
 * 返回结果类型
 *
 * @author Mr.Li yz
 * @date 2019/10/22
 */
public interface IResultEnum {

    /**
     * 获取枚举名
     *
     * @return
     */
    String name();

    /**
     * 获取枚举消息
     *
     * @return
     */
    String message();

    /**
     * 获取枚举值
     *
     * @return
     */
    Integer value();
}
