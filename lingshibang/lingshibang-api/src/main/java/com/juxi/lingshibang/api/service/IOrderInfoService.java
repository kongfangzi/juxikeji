package com.juxi.lingshibang.api.service;

import com.juxi.lingshibang.common.base.Result;
import com.juxi.lingshibang.api.entity.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.juxi.lingshibang.api.vo.OrderInfoVO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * <p>
 * 订单信息表 服务类
 * </p>
 *
 * @author liufeng
 * @since 2021-01-10
 */
public interface IOrderInfoService extends IService<OrderInfo> {

    @Transactional
    Result saveOrUpdate(@Valid @RequestBody OrderInfoVO orderInfoVO) throws Exception;

    @Transactional
    Result receiveOrder(@Valid @RequestBody OrderInfoVO orderInfoVO) throws Exception;
}
