
package com.juxi.lingshibang.api.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录表单
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@ApiModel(value = "登录表单")
public class LoginForm {
    @ApiModelProperty(value = "手机号")
    @NotBlank(message="请输入手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    @NotBlank(message="请输入密码")
    private String password;

}
