package com.juxi.lingshibang.admin.utilobject.dto;

import com.juxi.lingshibang.common.model.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@ApiModel("角色相关信息")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RoleDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value ="角色Id")
    private String id;

    /**
     * 角色名称
     */
//    @NotEmpty(message="角色名称不能为空")
    @ApiModelProperty(value ="角色名称")
    private String name;

    /**
     * 描述
     */
//    @NotEmpty(message="描述不能为空")
    @ApiModelProperty(value ="描述")
    private String description;

    /**
     * 权限ids
     */
    @ApiModelProperty(value ="权限ids")
    private List<String> permissionIds;

    /**
     * 部门ids
     */
    @ApiModelProperty(value ="部门ids")
    private List<String> deptIds;

    /**
     * 用户ids
     */
    @ApiModelProperty(value ="用户ids")
    private List<String> userIds;

}
