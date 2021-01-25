package com.juxi.lingshibang.common.enums;

/**
 * 对象状态
 * @date 2020-03-06
 */
public enum ObjectStatusEnum implements IResultEnum {
    NORMAL(0, "正常"),
    DELETE(1, "删除"),
    ;
    private int code;
    private String desc;

    ObjectStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ObjectStatusEnum getCode(int code) {
        for (ObjectStatusEnum s : ObjectStatusEnum.values()) {
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
