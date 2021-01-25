package com.juxi.lingshibang.admin.mapper;

import com.juxi.lingshibang.admin.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.juxi.lingshibang.common.model.dto.LoginUserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户账号表 Mapper 接口
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户Id查询用户角色
     * @param userId
     * @param removed
     * @return
     */
    List<String> getUserRolesByUserId(@Param("userId") String userId,
                                      @Param("removed") Integer removed);

    /**
     * 密码重置
     * @param password
     * @param ids
     * @return
     */
    Integer passwordReset(@Param("password") String password,
                          @Param("ids")      String[] ids,
                          @Param("updator")  String updator);

    /**
     * 根据用户ID查询用户相关信息
     * @param userId
     * @return
     */
    LoginUserDTO loginUserInfo(@Param("userId") String userId,
                               @Param("removed") Integer removed);

    List<User> getUsersByRoleIds(@Param("roleIds") List<String> roleIds);

    List<User> selectByCondition(@Param("params") Map<String, String> params);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(String userId);
}
