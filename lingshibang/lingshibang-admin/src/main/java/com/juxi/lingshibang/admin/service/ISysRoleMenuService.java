package com.juxi.lingshibang.admin.service;

import com.juxi.lingshibang.admin.entity.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色和菜单关联表 服务类
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {

    void saveOrUpdate(String roleId, List<String> menuIdList);

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<String> queryMenuIdList(String roleId);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(String[] roleIds);

}
