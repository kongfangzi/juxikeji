package com.juxi.lingshibang.common.enums;

/**
 * 操作模块
 */
public enum ModuleEnum {

    LOGIN(1001, "用户登录", "用户管理"),

    LOGINOUT(1002, "退出登录", "用户管理"),

    PASSWORDRESET(1003, "密码重置", "用户管理"),

    PRODUCE_BOM_MAIN_SAVE(1031, "物料清单保存", "生产建模"),

    PRODUCE_BOM_MAIN_DELETE(1032, "物料清单删除", "生产建模"),

    PRODUCE_ROUTE_MAIN_SAVE(1033, "工艺路线保存", "生产建模"),

    PRODUCE_ROUTE_MAIN_DELETE(1034, "工艺路线删除", "生产建模"),

    ;

    /**
     * 操作功能Id
     */
    private final Integer operationId;

    /**
     * 操作功能名称
     */
    private final String operationName;

    /**
     * 操作模块名称
     */
    private final String moduleName;

    ModuleEnum(Integer operationId, String operationName, String moduleName) {
        this.operationId = operationId;
        this.operationName = operationName;
        this.moduleName = moduleName;
    }

    public static ModuleEnum getOperation(Integer operationId) {
        for (ModuleEnum me : ModuleEnum.values()) {
            if (me.operationId == operationId) {
                return me;
            }
        }
        throw new IllegalArgumentException("wrong object status operationId: " + operationId);
    }

    public Integer getOperationId() {
        return operationId;
    }

    public String getOperationName() {
        return operationName;
    }

    public String getModuleName() {
        return moduleName;
    }
}
