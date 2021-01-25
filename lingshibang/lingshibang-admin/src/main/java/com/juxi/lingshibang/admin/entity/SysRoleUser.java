package com.juxi.lingshibang.admin.entity;

import com.juxi.lingshibang.common.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
public class SysRoleUser {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableField("userId")
    private String userId;

    /**
     * 角色ID
     */
    @TableField("roleId")
    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "SysRoleUser{" +
            "userId=" + userId +
            ", roleId=" + roleId +
        "}";
    }
}
