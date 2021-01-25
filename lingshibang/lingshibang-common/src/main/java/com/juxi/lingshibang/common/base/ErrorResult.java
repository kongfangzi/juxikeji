package com.juxi.lingshibang.common.base;

import lombok.Data;

import java.io.Serializable;

/**
 * 错误结果响应
 * @author yks
 * @date 2020-03-19
 */
@Data
public class ErrorResult implements Serializable {
    private String timestamp;
    private String path;
    private Integer status;
    private String error;
    private String message;
}
