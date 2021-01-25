package com.juxi.lingshibang.common.enums;

/**
 * 权限类型
 */
public enum PermissionTypeEnum {

    MENU(1, "菜单"),
    BUTTON(2, "按钮"),
    DATA(3, "数据"),
    ;
    private int code;
    private String desc;

    PermissionTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static PermissionTypeEnum getCode(int code) {
        for (PermissionTypeEnum s : PermissionTypeEnum.values()) {
            if (s.code == code) {
                return s;
            }
        }
        throw new IllegalArgumentException("wrong permissionType status code: " + code);
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
