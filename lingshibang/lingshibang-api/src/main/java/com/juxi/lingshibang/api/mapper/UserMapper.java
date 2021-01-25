package com.juxi.lingshibang.api.mapper;

import com.juxi.lingshibang.api.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户账号表 Mapper 接口
 * </p>
 *
 * @author liufeng
 * @since 2021-01-10
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
