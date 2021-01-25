package com.juxi.lingshibang.admin.service.impl;

import com.juxi.lingshibang.admin.entity.OrderActionLog;
import com.juxi.lingshibang.admin.mapper.OrderActionLogMapper;
import com.juxi.lingshibang.admin.service.IOrderActionLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单操作日志 服务实现类
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@Service
public class OrderActionLogServiceImpl extends ServiceImpl<OrderActionLogMapper, OrderActionLog> implements IOrderActionLogService {

}
