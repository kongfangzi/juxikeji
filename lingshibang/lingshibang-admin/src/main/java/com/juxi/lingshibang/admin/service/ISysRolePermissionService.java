package com.juxi.lingshibang.admin.service;

import com.juxi.lingshibang.admin.entity.SysRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.juxi.lingshibang.admin.utilobject.dto.RolePermissionDTO;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 角色权限表 服务类
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
public interface ISysRolePermissionService extends IService<SysRolePermission> {

    @Transactional
    void addOrUpdatePermissionsToRole(RolePermissionDTO rolePermissionDTO) throws Exception ;

    int deleteBatchByRoleIds(String[] roleIds);
}
