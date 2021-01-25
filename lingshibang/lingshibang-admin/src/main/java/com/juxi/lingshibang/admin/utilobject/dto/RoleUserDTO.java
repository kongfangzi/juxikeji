package com.juxi.lingshibang.admin.utilobject.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class RoleUserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @ApiModelProperty(value ="角色ID")
    private String roleId;

    /**
     * 用户IDs
     */
    @ApiModelProperty(value ="用户IDs")
    private List<String> userIds;
}
