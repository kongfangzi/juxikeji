package com.juxi.lingshibang.common.model.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(value = JsonInclude.Include.ALWAYS)
@ApiModel("用户登录信息")
public class LoginUserDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    private String id;

    /**
     * 企业Id
     */
    private String companyId;

    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 部门Id
     */
    private String depId;

    /**
     * 部门名称
     */
    private String depName;

    /**
     * 姓名
     */
    private String username;

    /**
     * 登录名
     */
    private String nickname;

    /**
     * 头像
     */
    private String headimgurl;

    /**
     * 手机号
     */
    private String telPhone;

    /**
     * 类型 1：企业管理员  2：操作员
     */
    private Integer type;

    /**
     * 到期时间
     */
    private LocalDateTime dueTime;

    /**
     * 数据库名称
     */
    private String dbName;


}
