package com.juxi.lingshibang.admin.service.impl;

import com.juxi.lingshibang.admin.entity.Follows;
import com.juxi.lingshibang.admin.mapper.FollowsMapper;
import com.juxi.lingshibang.admin.service.IFollowsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户粉丝表和关注表 服务实现类
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@Service
public class FollowsServiceImpl extends ServiceImpl<FollowsMapper, Follows> implements IFollowsService {

}
