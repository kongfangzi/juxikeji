package com.juxi.lingshibang.common.base.exceptions;

import com.juxi.lingshibang.common.base.Result;
import com.juxi.lingshibang.common.base.enums.CommonCodeEnum;
import com.juxi.lingshibang.common.enums.IResultEnum;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import lombok.Getter;

/**
 * 基础异常信息
 *
 * @author Mr.Li yz
 * @date 2019/10/22
 */
@Getter
public class BaseException extends HystrixBadRequestException {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8321149154706648074L;

    /**
     * 异常对应的错误类型
     * @deprecated 此属性在抛出 转换成 result 后 将 转换不回 BaseException  的 resultEnum
     * 建议 在 BaseException 中 用 code 和 message(父类中的) 表示
     */
    private final IResultEnum resultEnum;
    /**
     * 对应的异常数据
     */
    private Object data;

    /**
     * 异常码
     */
    private Integer code;
    /**
     * 时间戳
     */
    private Long time;


    /**
     * 默认是系统异常
     */
    public BaseException() {
        super(CommonCodeEnum.SYSTEM_FAILURE.message());
        this.code = CommonCodeEnum.SYSTEM_FAILURE.value();
        this.resultEnum = CommonCodeEnum.SYSTEM_FAILURE;
        this.time = System.currentTimeMillis();
    }

    /**
     * 系统异常+异常说明
     *
     * @param message
     */
    public BaseException(String message) {
        super(message);
        this.code = CommonCodeEnum.SYSTEM_FAILURE.value();
        this.resultEnum = CommonCodeEnum.SYSTEM_FAILURE;
        this.time = System.currentTimeMillis();
    }

    /**
     * 系统异常+异常信息
     *
     * @param cause
     */
    public BaseException(Throwable cause) {
        super(cause.getMessage(),cause);
        this.code = CommonCodeEnum.SYSTEM_FAILURE.value();
        this.resultEnum = CommonCodeEnum.SYSTEM_FAILURE;
        this.time = System.currentTimeMillis();
    }

    /**
     * 异常枚举
     *
     * @param resultEnum
     */
    public BaseException(IResultEnum resultEnum) {
        super(resultEnum.message());
        this.code = resultEnum.value();
        this.resultEnum = resultEnum;
        this.time = System.currentTimeMillis();
    }

    /**
     * 异常枚举+异常数据
     *
     * @param resultEnum
     * @param data
     */
    public BaseException(IResultEnum resultEnum, Object data) {
        super(resultEnum.message());
        this.code = resultEnum.value();
        this.data = data;
        this.resultEnum = resultEnum;
        this.time = System.currentTimeMillis();

    }

    /**
     * 枚举异常+异常信息
     *
     * @param resultEnum
     * @param t
     */
    public BaseException(IResultEnum resultEnum, Throwable t) {
        super(resultEnum.message(), t);
        this.code = resultEnum.value();
        this.resultEnum = resultEnum;
        this.time = System.currentTimeMillis();
    }

    /**
     * 异常枚举+异常描述+异常信息
     *
     * @param resultEnum
     * @param message
     * @param cause
     */
    public BaseException(IResultEnum resultEnum, String message, Throwable cause) {
        super(message, cause);
        this.code = resultEnum.value();
        this.resultEnum = resultEnum;
        this.time = System.currentTimeMillis();
    }

    public BaseException(Integer code, String message,Object data,Long time) {
        super(message);
        this.code = code;
        this.data = data;
        this.resultEnum =null;
        this.time = time;

    }

    public BaseException(Result result) {
        super(result.getMsg());
        this.code = result.getCode();
        this.data = result.getData();
        this.resultEnum =null;
        this.time = result.getTime();
    }

}
