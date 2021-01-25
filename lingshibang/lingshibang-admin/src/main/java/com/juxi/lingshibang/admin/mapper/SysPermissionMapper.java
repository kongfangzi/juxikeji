package com.juxi.lingshibang.admin.mapper;

import com.juxi.lingshibang.admin.entity.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.juxi.lingshibang.common.model.dto.UserPermissionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 根据用户ID查询用户权限
     * @param userId
     * @param device
     * @return
     */
    List<UserPermissionDTO> listByUserId(@Param("userId") String userId,
                                         @Param("device") Integer device,
                                         @Param("type")  Integer  type,
                                         @Param("isRemoved") Integer isRemoved);

    /**
     * 根据用户ID查询权限
     * @param companyId
     * @param device
     * @param type
     * @param isDelete
     * @return
     */
    List<UserPermissionDTO>  listByCompanyId(@Param("companyId") String companyId,
                                             @Param("device") Integer device,
                                             @Param("type")  Integer  type,
                                             @Param("isDelete") Integer isDelete);
}
