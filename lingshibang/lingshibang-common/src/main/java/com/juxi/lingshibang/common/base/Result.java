package com.juxi.lingshibang.common.base;

import lombok.Data;

import java.io.Serializable;


/**
 * 结果集
 * @author Administrator
 */
@Data
public class Result<T> implements Serializable {
    public static final Integer SUCCESS_CODE = 200;

    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;
    private T data;
    private Long time;

    public Result() {
    }


    public Result(int code, String msg, T data, Long time) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.time = time;
    }

    /**
     * @param code
     */
    public Result(int code) {
        super();
        this.code = code;
    }

    /**
     * @param code
     * @param msg
     */
    public Result(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    /**
     * @param code
     * @param msg
     * @param data
     */
    public Result(int code, String msg, T data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


}
