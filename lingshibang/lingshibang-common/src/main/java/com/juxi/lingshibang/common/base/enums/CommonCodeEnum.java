package com.juxi.lingshibang.common.base.enums;

import com.juxi.lingshibang.common.enums.IResultEnum;
import lombok.Getter;

/**
 * @author xxy
 * @version $Id: CommonCodeEnum.java
 */
@Getter
public enum CommonCodeEnum implements IResultEnum {

    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 系统异常
     */
    SYSTEM_ERROR(10002, "系统异常，请联系管理员！"),

    /**
     * 外部接口调用异常
     */
    FACE_SYSTEM_ERROR(10003, "外部接口调用异常，请联系管理员！"),

    /**
     * 业务连接处理超时
     */
    CONNECT_TIME_OUT(10004, "系统超时，请稍后再试!"),

    /**
     * 系统错误
     */
    SYSTEM_FAILURE(10005, "系统错误"),

    /**
     * 参数为空
     */
    NULL_ARGUMENT(10006, "参数为空"),

    /**
     * 参数不正确
     */
    ILLEGAL_ARGUMENT(10007, "参数不正确"),

    /**
     * 逻辑错误
     */
    LOGIC_ERROR(10008, "逻辑错误"),

    /**
     * 无操作权限
     */
    NO_OPERATE_PERMISSION(10009, "无操作权限"),

    /**
     * SESSION超时
     */
    SESSION_TIMEOUT(10010, "请登录"),

    /**
     * 数据异常
     */
    DATA_ERROR(10011, "数据异常"),

    /**
     * 远程调用异常
     */
    REMOTE_CALL_ERROR(10013, "远程调用异常"),

    /**
     * DB事务异常
     */
    DB_TRANSACTION_ERROR(10014, "DB事务异常"),

    /**
     * 重复请求
     */
    REPEAT_REQUEST_ERROR(10015, "重复请求异常"),

    /**
     * 暂不支持的操作
     */
    UN_SUPPORT_OPERATE(10016, "暂不支持的操作"),
    ;



    /**
     * 错误码
     */
    protected Integer value;
    /**
     * 描述信息
     */
    protected String message;

    /**
     * 构造方法
     */
    CommonCodeEnum(int value, String message) {
        this.value = value;
        this.message = message;
    }

    /**
     * 通过枚举<code>code</code>获得枚举。
     *
     * @param code 错误码
     * @return
     */
    public static CommonCodeEnum getEnumByCode(int code) {
        for (CommonCodeEnum param : values()) {
            if (code == param.getValue()) {
                return param;
            }
        }
        return null;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public Integer value() {
        return value;
    }

}
