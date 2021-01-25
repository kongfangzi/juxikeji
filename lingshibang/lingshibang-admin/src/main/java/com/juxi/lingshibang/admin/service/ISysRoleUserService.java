package com.juxi.lingshibang.admin.service;

import com.juxi.lingshibang.admin.entity.SysRoleUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.juxi.lingshibang.admin.utilobject.dto.RoleUserDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
public interface ISysRoleUserService extends IService<SysRoleUser> {

    @Transactional
    void addOrUpdateRoleUser(String userId, List<String> roleIds);

    @Transactional
    void addOrUpdateUsersToRole(RoleUserDTO roleUserDTO) throws Exception;

    int deleteBatchByUserId(String[] userId);

    int deleteByRoleUser(String userId, String roleId);
}
