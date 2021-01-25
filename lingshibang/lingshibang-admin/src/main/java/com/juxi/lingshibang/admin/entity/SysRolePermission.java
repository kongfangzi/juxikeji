package com.juxi.lingshibang.admin.entity;

import com.juxi.lingshibang.common.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 * 角色权限表
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
public class SysRolePermission {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableField("roleId")
    private String roleId;

    /**
     * 权限ID
     */
    @TableField("permissionId")
    private String permissionId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "SysRolePermission{" +
            "roleId=" + roleId +
            ", permissionId=" + permissionId +
        "}";
    }
}
