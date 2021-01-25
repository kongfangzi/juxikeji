package com.juxi.lingshibang.api.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderInfoVO {

    /**
     * 订单id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单编码
     */
    private String orderCode;

    /**
     * 接单人用户编码
     */
    private String receiveUserCode;

    /**
     * 工作类型：0居家工作，1甲方现场工作
     */
    private Integer workType;

    /**
     * 订单类型：0样衣加工，1制版，2设计
     */
    private Integer orderType;

    /**
     * 服装类目id
     */
    private Integer categoryId;

    /**
     * 价格区间（最低）
     */
    private BigDecimal minPrice;

    /**
     * 价格区间（最高）
     */
    private BigDecimal maxPrice;

    /**
     * 订单总金额
     */
    private BigDecimal totalPrice;

    /**
     * 奖励金额支付
     */
    private BigDecimal rewardPay;

    /**
     * 实际付款额（分摊）
     */
    private BigDecimal actualPay;

    /**
     * 余额支付
     */
    private BigDecimal balancePay;

    /**
     * 优惠券支付
     */
    private BigDecimal couponPay;

    /**
     * 积分支付
     */
    private BigDecimal scorePay;

    /**
     * 运费总价
     */
    private BigDecimal freight;

    /**
     * 订单的状态： 0 进行中 1 交易完成 2 已取消 3 无效 4 退货
     */
    private Integer orderStatus;

    /**
     * 支付状态; 0待付款; 1支付中（分次支付）; 2已付款
     */
    private Integer payStatus;

    /**
     * 支付方式 ：0 支付宝 1微信  2银联  3余额支付
     */
    private Integer payType;

    /**
     * 仲裁状态：0未仲裁，1仲裁中，2仲裁通过，3仲裁失败
     */
    private Integer arbitrateStatus;

    /**
     * 配送状态：0未发货, 1已发货, 2已收货
     */
    private Integer shippingStatus;

    /**
     * 退款标记：0未退，1退款/退货进行中， 2 退款完成， 3 待支付宝URL手动退款 4 支付宝退款处理中
     */
    private Integer returnMark;

    /**
     * 地区编码
     */
    private String areaCode;

    /**
     * 地区详情
     */
    private String areaDetail;

    /**
     * 寄件人姓名
     */
    private String senderName;

    /**
     * 寄件人电话
     */
    private String senderPhone;

    /**
     * 寄件人地址
     */
    private String senderAddress;

    /**
     * 收件人名称
     */
    private String receivorName;

    /**
     * 收件人电话
     */
    private String receivorPhone;

    /**
     * 收件人地址
     */
    private String receivorAddress;

    /**
     * 订单来源：0微信小程序，1 安卓app，2 iOS app，3 PC端
     */
    private Integer source;

    /**
     * 订单是否已提醒发货：0否，1是
     */
    private Integer remindDelive;

    /**
     * 费率类型：
     */
    private Integer rateType;

    /**
     * 是否买家删除：0否，1是
     */
    private Integer buyerDelete;

    /**
     * 仲裁费
     */
    private BigDecimal arbitrateFee;

    /**
     * 仲裁结果说明
     */
    private String arbitrateResult;

    /**
     * 铺货、分销订单相互关联订单ID
     */
    private Integer refOrderId;

    /**
     * 用来记录订单属性数据
     */
    private String attribute;

    /**
     * 订单佣金费率：由派单人承担
     */
    private BigDecimal poundageRate;

    /**
     * 空闲时间（开始）
     */
    private LocalDateTime startFreeTime;

    /**
     * 空闲时间（结束）
     */
    private LocalDateTime endFreeTime;
}
