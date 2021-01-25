package com.juxi.lingshibang.common.enums;
/**
 * 设备端
 */
public enum DeviceEnum {

    PC(1, "PC端"),
    MOBILE(2, "移动端"),
            ;
    private int code;
    private String desc;

    DeviceEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static DeviceEnum getCode(int code) {
        for (DeviceEnum s : DeviceEnum.values()) {
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
}
