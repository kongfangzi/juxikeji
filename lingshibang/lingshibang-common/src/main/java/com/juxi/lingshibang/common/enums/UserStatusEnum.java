package com.juxi.lingshibang.common.enums;

public enum UserStatusEnum implements IResultEnum{
    LOGICDEL(0, "逻辑删除"),
    EFFECT(1, "生效"),
    ;
    private int code;
    private String desc;

    UserStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static UserStatusEnum getCode(int code) {
        for (UserStatusEnum s : UserStatusEnum.values()) {
            if (s.code == code) {
                return s;
            }
        }
        throw new IllegalArgumentException("wrong object status code: " + code);
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String message() {
        return message();
    }

    @Override
    public Integer value() {
        return code;
    }
}
