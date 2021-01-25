package com.juxi.lingshibang.api.service.impl;

import com.juxi.lingshibang.api.entity.Follows;
import com.juxi.lingshibang.api.mapper.FollowsMapper;
import com.juxi.lingshibang.api.service.IFollowsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户粉丝表和关注表 服务实现类
 * </p>
 *
 * @author liufeng
 * @since 2021-01-10
 */
@Service
public class FollowsServiceImpl extends ServiceImpl<FollowsMapper, Follows> implements IFollowsService {

}
