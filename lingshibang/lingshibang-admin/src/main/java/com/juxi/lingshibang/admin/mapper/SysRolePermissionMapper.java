package com.juxi.lingshibang.admin.mapper;

import com.juxi.lingshibang.admin.entity.SysRolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色权限表 Mapper 接口
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@Mapper
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {

    int deleteBatch(String[] roleIds);
}
