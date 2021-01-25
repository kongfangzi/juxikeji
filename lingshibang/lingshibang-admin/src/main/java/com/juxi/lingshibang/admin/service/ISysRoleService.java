package com.juxi.lingshibang.admin.service;

import com.juxi.lingshibang.admin.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.juxi.lingshibang.admin.utilobject.dto.RoleDTO;
import com.juxi.lingshibang.common.base.Result;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
public interface ISysRoleService extends IService<SysRole> {

    Result<SysRole> saveOrUpdate(RoleDTO record);

    List<SysRole> getListByIds(List<String> ids);

    Result delLogicListById(String id);

}
