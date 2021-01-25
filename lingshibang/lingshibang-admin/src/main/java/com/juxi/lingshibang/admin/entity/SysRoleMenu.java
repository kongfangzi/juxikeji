package com.juxi.lingshibang.admin.entity;

import com.juxi.lingshibang.common.model.BaseEntity;

/**
 * <p>
 * 角色和菜单关联表
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
public class SysRoleMenu {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 菜单ID
     */
    private String menuId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "SysRoleMenu{" +
            "roleId=" + roleId +
            ", menuId=" + menuId +
        "}";
    }
}
