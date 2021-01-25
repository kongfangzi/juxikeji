package com.juxi.lingshibang.admin.utilobject.dto;

import com.juxi.lingshibang.common.model.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("登录")
@Data
public class LoginDTO extends BaseDTO {

    @ApiModelProperty(value ="用户编号")
    private String userCode;
    @ApiModelProperty(value ="手机号")
    private String telPhone;
    @ApiModelProperty(value ="密码")
    private String password;
    @ApiModelProperty(value ="验证码")
    private String verificationCode;
}
