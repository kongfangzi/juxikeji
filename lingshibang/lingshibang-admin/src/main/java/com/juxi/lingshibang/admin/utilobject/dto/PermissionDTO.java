package com.juxi.lingshibang.admin.utilobject.dto;

import com.juxi.lingshibang.common.model.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@ApiModel("权限相关信息")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PermissionDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value ="id")
    private String id;

    /**
     * 父ID
     */
//    @NotEmpty(message="父ID不能为空")
    @ApiModelProperty(value ="父ID")
    private String parentId;

    /**
     * 资源名
     */
//    @NotEmpty(message="资源名不能为空")
    @ApiModelProperty(value ="资源名")
    private String name;

    /**
     * 样式
     */
//    @NotEmpty(message="样式不能为空")
    @ApiModelProperty(value ="样式")
    private String css;

    /**
     * 访问连接
     */
//    @NotEmpty(message="访问连接不能为空")
    @ApiModelProperty(value ="访问连接")
    private String href;

    /**
     * 设备端 1：PC端 2 ：移动端
     */
//    @NotEmpty(message="设备端不能为空")
    @ApiModelProperty(value ="设备端 1：PC端 2 ：移动端")
    private Boolean device;

    /**
     * 类型(1： 菜单 2 ：按钮  3：数据)
     */
//    @NotEmpty(message="类型不能为空")
    @ApiModelProperty(value ="类型(1： 菜单 2 ：按钮  3：数据)")
    private Boolean type;

    /**
     * 权限
     */
//    @NotEmpty(message="权限不能为空")
    @ApiModelProperty(value ="权限")
    private String permission;

    /**
     * 排序
     */
//    @NotEmpty(message="排序不能为空")
    @ApiModelProperty(value ="排序")
    private Integer sort;

    /**
     * 子节点
     */
    @ApiModelProperty(value ="子节点")
    private List<PermissionDTO> children;
}
