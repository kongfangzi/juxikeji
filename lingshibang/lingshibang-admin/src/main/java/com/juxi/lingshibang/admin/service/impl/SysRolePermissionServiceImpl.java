package com.juxi.lingshibang.admin.service.impl;

import com.juxi.lingshibang.admin.entity.SysRolePermission;
import com.juxi.lingshibang.admin.mapper.SysRolePermissionMapper;
import com.juxi.lingshibang.admin.service.ISysRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juxi.lingshibang.admin.utilobject.dto.RolePermissionDTO;
import com.juxi.lingshibang.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色权限表 服务实现类
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements ISysRolePermissionService {

    @Autowired
    private SysRolePermissionMapper rolePermissionMapper;

    /**
     * 给角色绑定多权限
     * @param rolePermissionDTO
     * @throws Exception
     */
    @Override
    public void addOrUpdatePermissionsToRole(RolePermissionDTO rolePermissionDTO) throws Exception {

        if(StringUtil.isEmpty(rolePermissionDTO.getRoleId()) || rolePermissionDTO.getPermissionIds().size() == 0){
            return ;
        }

        //先删除角色与权限关系
        deleteBatchByRoleIds(new String[] {rolePermissionDTO.getRoleId()});

        List<String> permissionIds = rolePermissionDTO.getPermissionIds();
        //保存角色与多权限关系
        for(String permissionId : permissionIds){
            SysRolePermission rolePermission = new SysRolePermission();
            rolePermission.setPermissionId(permissionId);
            rolePermission.setRoleId(rolePermissionDTO.getRoleId());
            rolePermissionMapper.insert(rolePermission);
        }
    }

    @Override
    public int deleteBatchByRoleIds(String[] roleIds){
        return rolePermissionMapper.deleteBatch(roleIds);
    }
}
