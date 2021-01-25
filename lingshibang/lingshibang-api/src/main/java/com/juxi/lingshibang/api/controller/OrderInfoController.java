package com.juxi.lingshibang.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.juxi.lingshibang.api.entity.OrderInfo;
import com.juxi.lingshibang.common.base.Result;
import com.juxi.lingshibang.api.service.IOrderInfoService;
import com.juxi.lingshibang.api.vo.OrderInfoVO;
import com.juxi.lingshibang.common.util.login.CurrentUserUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.juxi.lingshibang.common.base.BaseController;

import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 * 订单信息表 前端控制器
 * </p>
 *
 * @author liufeng
 * @since 2021-01-10
 */
@RestController
@RequestMapping("/order-info")
public class OrderInfoController extends BaseController {

    @Autowired
    private IOrderInfoService orderInfoService;

    @Autowired
    private CurrentUserUtil currentUserUtil;

    @ApiOperation(value = "订单新增或者修改")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody OrderInfoVO orderInfoVO) throws Exception {
        return orderInfoService.saveOrUpdate(orderInfoVO);
    }

    @ApiOperation(value = "接单")
    @PostMapping("/receiveOrder")
    public Result receiveOrder(@RequestBody OrderInfoVO orderInfoVO) throws Exception {
        return orderInfoService.receiveOrder(orderInfoVO);
    }

    @ApiOperation(value = "派单人订单列表")
    @PostMapping("/send/list")
    public Result<List<OrderInfo>> sendOrderList() throws Exception {
        String userId = currentUserUtil.getLoginUser().getId();
        QueryWrapper<OrderInfo> query = new QueryWrapper<>();
        query.eq("creator", userId);
        Result result = new Result();
        result.setData(orderInfoService.list(query));
        return result;
    }

    @ApiOperation(value = "接单人订单列表")
    @PostMapping("/receive/list")
    public Result<List<OrderInfo>> orderList() throws Exception {
        String userId = currentUserUtil.getLoginUser().getId();
        QueryWrapper<OrderInfo> query = new QueryWrapper<>();
        query.eq("receive_user_id", userId);
        Result result = new Result();
        result.setData(orderInfoService.list(query));
        return result;
    }
}
