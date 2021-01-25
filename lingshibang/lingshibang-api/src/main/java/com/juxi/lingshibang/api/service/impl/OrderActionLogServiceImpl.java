package com.juxi.lingshibang.api.service.impl;

import com.juxi.lingshibang.api.entity.OrderActionLog;
import com.juxi.lingshibang.api.mapper.OrderActionLogMapper;
import com.juxi.lingshibang.api.service.IOrderActionLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单操作日志 服务实现类
 * </p>
 *
 * @author liufeng
 * @since 2021-01-10
 */
@Service
public class OrderActionLogServiceImpl extends ServiceImpl<OrderActionLogMapper, OrderActionLog> implements IOrderActionLogService {

}
