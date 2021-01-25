package com.juxi.lingshibang.common.exception;

import com.juxi.lingshibang.common.enums.ErrorCodeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 业务异常
 * @author yks
 * @date 2019-09-23
 */

@Getter
@NoArgsConstructor
public class BizException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -1L;
    private static final Integer FAIL = 400;
    /**
     * 错误码
     */
    private Integer errorCode;

    public BizException(String message) {
        super(message);
        this.errorCode = FAIL;

    }

    public BizException(Throwable cause) {
        super(cause);
        this.errorCode = FAIL;
    }


    public BizException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BizException(ErrorCodeEnum errorCodeEnum) {
        this(errorCodeEnum.getErrorCode(), errorCodeEnum.getErrorMsg());
    }


}
