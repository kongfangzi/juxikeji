package com.juxi.lingshibang.common.enums;

/**
 * 用户类型
 */
public enum UserTypeEnum {

    ADMIN(0, "平台管理员"),
    ORDINARY(1, "普通用户"),
    ;
    private int code;
    private String desc;

    UserTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static UserTypeEnum getCode(int code) {
        for (UserTypeEnum s : UserTypeEnum.values()) {
            if (s.code == code) {
                return s;
            }
        }
        throw new IllegalArgumentException("wrong userType code: " + code);
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }


}
