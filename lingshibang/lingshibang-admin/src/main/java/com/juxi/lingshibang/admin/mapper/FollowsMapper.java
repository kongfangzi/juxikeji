package com.juxi.lingshibang.admin.mapper;

import com.juxi.lingshibang.admin.entity.Follows;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户粉丝表和关注表 Mapper 接口
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@Mapper
public interface FollowsMapper extends BaseMapper<Follows> {

}
