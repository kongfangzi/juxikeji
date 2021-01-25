package com.juxi.lingshibang.common.util;

import com.juxi.lingshibang.common.base.Result;
import com.juxi.lingshibang.common.enums.IResultEnum;
import com.juxi.lingshibang.common.enums.ErrorCodeEnum;
import com.juxi.lingshibang.common.exception.BizException;

/**
 * @Author 贺 小 洋
 * @Date Created in 2019/3/14
 * @Description 结果返回集合
 */
@SuppressWarnings("all")
public class ResponseUtil<T> {

    public static final int SUCCESS = 200;
    public static final int ERROR = 401;
    public static final int STATUS= 301;

    /**
     * 成功
     * @return
     */
    public static Result SUCCESS() {
        return new Result(SUCCESS);
    }

    public static Result SUCCESS(String message) { return new Result(SUCCESS, message); }

    public static Result SUCCESS(String message, Object data) {
        return new Result(SUCCESS, message, data);
    }

    public static Result SUCCESS(IResultEnum message) {
        return SUCCESS(message, null);
    }

    public static Result SUCCESS(IResultEnum message, Object data) {
        return new Result(message.value(), message.message(), data);
    }

    /**
     * 失败
     * @return
     */
    public static Result Failure() {
        return new Result(ERROR);
    }

    public static Result Failure(int code, String message) {
        return new Result(code, message);
    }

    public static Result Failure(String message) {
        return new Result(ERROR, message);
    }

    public static Result Failure(String message, Object data) {
        return new Result(ERROR, message, data);
    }

    public static Result Failure(IResultEnum message) {
        return Failure(message, null);
    }

    public static Result Failure(IResultEnum message, Object data) {
        return new Result(message.value(), message.message(), data);
    }

    public static Result Failure(ErrorCodeEnum codeEnum) {
        return new Result(codeEnum.getErrorCode(), codeEnum.getErrorMsg(), null);
    }

    public static Result Failure(BizException bizException) {
        return new Result(bizException.getErrorCode(), bizException.getMessage(), null);
    }

    /**
     * 消息提示
     */
    public static Result MESSAGE(String message) { return new Result(STATUS, message);}

    public static Result MESSAGE(String message, Object data) { return new Result(STATUS, message, data); }
}
