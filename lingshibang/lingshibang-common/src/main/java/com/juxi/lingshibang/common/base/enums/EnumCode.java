package com.juxi.lingshibang.common.base.enums;

/**
 * 枚举编码
 *
 * @author: Mr.Li yz
 * @create: 2019-10-28 15:04
 **/
public enum EnumCode {

    /**
     * 通用枚举
     */
    COMMON(100, "通用枚举"),
    /**
     * 用户域枚举
     */
    USER(101, "用户域枚举"),
    /**
     * 权限域枚举
     */
    AUTH(102, "权限域枚举"),
    /**
     * 工厂建模
     */
    CONSTRUCTION(103, "工厂建模"),
    ;

    /**
     * 枚举编码
     */
    protected Integer code;
    /**
     * 枚举说明
     */
    protected String message;

    /**
     * 构造方法
     */
    EnumCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
