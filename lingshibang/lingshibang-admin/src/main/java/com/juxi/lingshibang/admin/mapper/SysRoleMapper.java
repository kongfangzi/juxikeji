package com.juxi.lingshibang.admin.mapper;

import com.juxi.lingshibang.admin.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> getRoleByIds(@Param("ids") List<String> ids);

    Integer updateBatch(@Param("roleList") List<SysRole> roleList);
}
