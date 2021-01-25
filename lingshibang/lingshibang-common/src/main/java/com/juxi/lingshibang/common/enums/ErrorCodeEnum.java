package com.juxi.lingshibang.common.enums;

/**
 * 错误码表
 * @author yks
 * @date 2019-09-23
 */
public enum ErrorCodeEnum {

    INNER_ERROE(500, "系统内部异常"),
    ERROR_400(401, "参数不合法"),
    ACCESS_TOKEN_EMPTY(8011, "TOKEN为空"),
    LOGOUT(8012, "已登出"),
    ACCESS_TOKEN_INVALID(8013, "TOKEN无效"),
    ACCESS_TOKEN_EXPIRE(8014, "TOKEN过期"),
    USER_NOT_EXIST(8015,"当前用户不存在"),
    ;

    private Integer errorCode;
    private String errorMsg;

    ErrorCodeEnum(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public static String getError(Integer errorCode) {
        for (ErrorCodeEnum e : ErrorCodeEnum.values()) {
            if (errorCode.intValue() == e.getErrorCode().intValue()) {
                return e.getErrorMsg();
            }
        }
        return "未知业务异常";
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

}
