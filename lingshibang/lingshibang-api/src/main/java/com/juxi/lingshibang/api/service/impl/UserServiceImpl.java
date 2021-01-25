package com.juxi.lingshibang.api.service.impl;

import com.juxi.lingshibang.api.entity.User;
import com.juxi.lingshibang.api.mapper.UserMapper;
import com.juxi.lingshibang.api.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账号表 服务实现类
 * </p>
 *
 * @author liufeng
 * @since 2021-01-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
