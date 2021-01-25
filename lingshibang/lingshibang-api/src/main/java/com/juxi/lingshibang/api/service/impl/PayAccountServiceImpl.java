package com.juxi.lingshibang.api.service.impl;

import com.juxi.lingshibang.api.entity.PayAccount;
import com.juxi.lingshibang.api.mapper.PayAccountMapper;
import com.juxi.lingshibang.api.service.IPayAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付账户表 服务实现类
 * </p>
 *
 * @author liufeng
 * @since 2021-01-10
 */
@Service
public class PayAccountServiceImpl extends ServiceImpl<PayAccountMapper, PayAccount> implements IPayAccountService {

}
