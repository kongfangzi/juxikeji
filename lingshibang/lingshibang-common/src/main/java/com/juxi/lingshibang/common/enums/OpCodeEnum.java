package com.juxi.lingshibang.common.enums;

/**
 * 系統操作
 */
public enum OpCodeEnum {

    OP_SUCCESS(200, "操作成功"),
    PWD_LESS_SIX(301, "密码长度不能小于6位！请重新修改！"),
    OP_FAILED(400, "操作失败"),
    PARAM_ERR(401, "参数错误"),
    NAME_PWD_WRONG(402, "用户名密码错误"),
    LOGIN_PWD_WRONG(403, "登陆密码不正确"),
    RUNTIME_ERR(404, "运行时错误"),
    DATABASE_ERR(405, "数据库错误"),
    REQUEST_PROCESSING(406, "该请求正在处理中，请稍后"),
    COMMUNICATION_EXCEPTION(407, "通信异常"),
    DATABASE_QUERY_EMPTY(408, "数据库查询为空！"),
    OP_OVERTIME(409,"操作超时"),
    OP_HISTORY_DATE(410,"无历史数据"),
    VISIT_TOO_FAST(411, "访问过于频繁"),
    ;

    private int code;
    private String msg;

    OpCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static OpCodeEnum getCode(int code) {
        for (OpCodeEnum c : values()) {
            if (c.code == code) {
                return c;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
