package com.juxi.lingshibang.admin.service;

import com.alibaba.fastjson.JSONArray;
import com.juxi.lingshibang.admin.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
public interface ISysPermissionService extends IService<SysPermission> {

    /**
     * 根据用户ID查询用户权限
     * @param userId
     * @return
     */
    JSONArray getPermissionsByUserId(String userId, Integer device);

    /**
     * 根据用户ID查询用户菜单权限
     * @param userId
     * @param device
     * @return
     */
    String[] getMenuIdsByUserId(String userId,Integer device);

    /**
     *根据设备端查询权限数
     * @return
     */
    JSONArray getAllPermissions(String userId,Integer device);
}
