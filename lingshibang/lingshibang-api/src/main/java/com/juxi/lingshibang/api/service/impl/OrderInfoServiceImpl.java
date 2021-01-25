package com.juxi.lingshibang.api.service.impl;

import com.juxi.lingshibang.common.base.Result;
import com.juxi.lingshibang.common.enums.ObjectStatusEnum;
import com.juxi.lingshibang.common.util.ResponseUtil;
import com.juxi.lingshibang.common.util.login.CurrentUserUtil;
import com.juxi.lingshibang.common.utils.StringUtils;
import com.juxi.lingshibang.api.entity.OrderInfo;
import com.juxi.lingshibang.api.mapper.OrderInfoMapper;
import com.juxi.lingshibang.api.mapper.UserMapper;
import com.juxi.lingshibang.api.service.IOrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juxi.lingshibang.api.vo.OrderInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单信息表 服务实现类
 * </p>
 *
 * @author liufeng
 * @since 2021-01-10
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired(required=true)
    private CurrentUserUtil currentUserUtil;

    /**
     * 派单
     * @param orderInfoVO
     * @return
     * @throws Exception
     */
    @Override
    public Result saveOrUpdate(@Valid OrderInfoVO orderInfoVO) throws Exception {
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(orderInfoVO, orderInfo);
        if (StringUtils.isNotEmpty(String.valueOf(orderInfo.getId())) && null != orderInfoMapper.selectById(orderInfo.getId())) {
            //更新
            orderInfo.setUpdateTime(LocalDateTime.now());
            orderInfo.setUpdator(currentUserUtil.getUserInfo().getUsername());
            orderInfoMapper.updateById(orderInfo);
            return ResponseUtil.SUCCESS("订单信息更新成功！", orderInfo);
        }
        //新增
        orderInfo.setBuyerDelete(ObjectStatusEnum.NORMAL.getCode());
        orderInfo.setCreateTime(LocalDateTime.now());
        orderInfo.setCreator(currentUserUtil.getUserInfo().getUsername());
        orderInfoMapper.insert(orderInfo);
        return ResponseUtil.SUCCESS("订单信息新增成功！", orderInfo);
    }

    //接单
    @Override
    public Result receiveOrder(@Valid OrderInfoVO orderInfoVO) throws Exception {
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(orderInfoVO, orderInfo);
//        User loginUser = userMapper.selectById(currentUserUtil.getUserInfo().getId());
//        if (StringUtils.isNotEmpty(loginUser.getInviterCode())) {
//            //计算分销分成
//        }
        orderInfo.setUpdateTime(LocalDateTime.now());
        orderInfo.setReceiveUserId(currentUserUtil.getUserInfo().getId());
        orderInfoMapper.updateById(orderInfo);
        return ResponseUtil.SUCCESS("接单成功成功！", orderInfo);
    }


}
