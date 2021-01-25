package com.juxi.lingshibang.common.enums;

public enum StatusEnum {
    SEND("1","已发送"),
    FAILED("2","接口调用失败"),
    NOT_SEND("0","未发送"),
    HAS_GLASS("1","是"),
    NO_GLASS("1","是"),
    HAS_HAT("1","是"),
    NO_HAT("0","否"),
    male("1","男"),
    female("2","女"),
    TOKEN_FAILED("001","请求token失败"),
    TOKEN_SUCCEED("002","请求token成功"),
    NO_SMILE("0","否"),
    SMILE("1","是"),
    SUCCESS("1","获取数据成功"),
    WRONG_PARAMS("020002","参数错误"),
    FAIL("020001","系统出现错误");
    private String code;

    private String message;

    StatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    private void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }
}
