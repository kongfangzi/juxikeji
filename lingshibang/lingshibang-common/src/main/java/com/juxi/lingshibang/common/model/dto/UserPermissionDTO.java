package com.juxi.lingshibang.common.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(value = JsonInclude.Include.ALWAYS)
@ApiModel("用户权限")
public class UserPermissionDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 父ID
     */
    private String parentId;

    /**
     * 资源名
     */
    private String name;

    /**
     * 样式
     */
    private String css;

    /**
     * 访问连接
     */
    private String href;

    /**
     * 设备端 1：PC端 2 ：移动端
     */
    private Integer device;

    /**
     * 类型(1： 菜单 2 ：按钮  3：数据)
     */
    private Integer type;

    /**
     * 权限
     */
    private String permission;

    /**
     * 排序
     */
    private Integer sort;


    /**
     * 子节点
     */
    private List<UserPermissionDTO> children;
}
