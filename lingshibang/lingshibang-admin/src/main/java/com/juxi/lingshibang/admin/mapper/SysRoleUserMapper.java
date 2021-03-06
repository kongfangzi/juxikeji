package com.juxi.lingshibang.admin.mapper;

import com.juxi.lingshibang.admin.entity.SysRoleUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@Mapper
public interface SysRoleUserMapper extends BaseMapper<SysRoleUser> {

    int deleteByRoleUser(String userId, String roleId);

    int deleteBatchByUserId(String[] userId);
}
