package com.juxi.lingshibang.admin.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.juxi.lingshibang.common.model.BaseEntity;

/**
 * <p>
 * 订单信息表
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@TableName("tb_order_info")
public class OrderInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

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

    /**
     * 订单创建时间
     */
    private LocalDateTime createTime;

    /**
     * 派单人用户编码
     */
    private String creator;

    /**
     * 订单更新时间
     */
    private LocalDateTime updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
    public String getReceiveUserCode() {
        return receiveUserCode;
    }

    public void setReceiveUserCode(String receiveUserCode) {
        this.receiveUserCode = receiveUserCode;
    }
    public Integer getWorkType() {
        return workType;
    }

    public void setWorkType(Integer workType) {
        this.workType = workType;
    }
    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }
    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    public BigDecimal getRewardPay() {
        return rewardPay;
    }

    public void setRewardPay(BigDecimal rewardPay) {
        this.rewardPay = rewardPay;
    }
    public BigDecimal getActualPay() {
        return actualPay;
    }

    public void setActualPay(BigDecimal actualPay) {
        this.actualPay = actualPay;
    }
    public BigDecimal getBalancePay() {
        return balancePay;
    }

    public void setBalancePay(BigDecimal balancePay) {
        this.balancePay = balancePay;
    }
    public BigDecimal getCouponPay() {
        return couponPay;
    }

    public void setCouponPay(BigDecimal couponPay) {
        this.couponPay = couponPay;
    }
    public BigDecimal getScorePay() {
        return scorePay;
    }

    public void setScorePay(BigDecimal scorePay) {
        this.scorePay = scorePay;
    }
    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }
    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }
    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
    public Integer getArbitrateStatus() {
        return arbitrateStatus;
    }

    public void setArbitrateStatus(Integer arbitrateStatus) {
        this.arbitrateStatus = arbitrateStatus;
    }
    public Integer getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(Integer shippingStatus) {
        this.shippingStatus = shippingStatus;
    }
    public Integer getReturnMark() {
        return returnMark;
    }

    public void setReturnMark(Integer returnMark) {
        this.returnMark = returnMark;
    }
    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
    public String getAreaDetail() {
        return areaDetail;
    }

    public void setAreaDetail(String areaDetail) {
        this.areaDetail = areaDetail;
    }
    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }
    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }
    public String getReceivorName() {
        return receivorName;
    }

    public void setReceivorName(String receivorName) {
        this.receivorName = receivorName;
    }
    public String getReceivorPhone() {
        return receivorPhone;
    }

    public void setReceivorPhone(String receivorPhone) {
        this.receivorPhone = receivorPhone;
    }
    public String getReceivorAddress() {
        return receivorAddress;
    }

    public void setReceivorAddress(String receivorAddress) {
        this.receivorAddress = receivorAddress;
    }
    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }
    public Integer getRemindDelive() {
        return remindDelive;
    }

    public void setRemindDelive(Integer remindDelive) {
        this.remindDelive = remindDelive;
    }
    public Integer getRateType() {
        return rateType;
    }

    public void setRateType(Integer rateType) {
        this.rateType = rateType;
    }
    public Integer getBuyerDelete() {
        return buyerDelete;
    }

    public void setBuyerDelete(Integer buyerDelete) {
        this.buyerDelete = buyerDelete;
    }
    public BigDecimal getArbitrateFee() {
        return arbitrateFee;
    }

    public void setArbitrateFee(BigDecimal arbitrateFee) {
        this.arbitrateFee = arbitrateFee;
    }
    public String getArbitrateResult() {
        return arbitrateResult;
    }

    public void setArbitrateResult(String arbitrateResult) {
        this.arbitrateResult = arbitrateResult;
    }
    public Integer getRefOrderId() {
        return refOrderId;
    }

    public void setRefOrderId(Integer refOrderId) {
        this.refOrderId = refOrderId;
    }
    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
    public BigDecimal getPoundageRate() {
        return poundageRate;
    }

    public void setPoundageRate(BigDecimal poundageRate) {
        this.poundageRate = poundageRate;
    }
    public LocalDateTime getStartFreeTime() {
        return startFreeTime;
    }

    public void setStartFreeTime(LocalDateTime startFreeTime) {
        this.startFreeTime = startFreeTime;
    }
    public LocalDateTime getEndFreeTime() {
        return endFreeTime;
    }

    public void setEndFreeTime(LocalDateTime endFreeTime) {
        this.endFreeTime = endFreeTime;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
            "id=" + id +
            ", orderCode=" + orderCode +
            ", receiveUserCode=" + receiveUserCode +
            ", workType=" + workType +
            ", orderType=" + orderType +
            ", categoryId=" + categoryId +
            ", minPrice=" + minPrice +
            ", maxPrice=" + maxPrice +
            ", totalPrice=" + totalPrice +
            ", rewardPay=" + rewardPay +
            ", actualPay=" + actualPay +
            ", balancePay=" + balancePay +
            ", couponPay=" + couponPay +
            ", scorePay=" + scorePay +
            ", freight=" + freight +
            ", orderStatus=" + orderStatus +
            ", payStatus=" + payStatus +
            ", payType=" + payType +
            ", arbitrateStatus=" + arbitrateStatus +
            ", shippingStatus=" + shippingStatus +
            ", returnMark=" + returnMark +
            ", areaCode=" + areaCode +
            ", areaDetail=" + areaDetail +
            ", senderName=" + senderName +
            ", senderPhone=" + senderPhone +
            ", senderAddress=" + senderAddress +
            ", receivorName=" + receivorName +
            ", receivorPhone=" + receivorPhone +
            ", receivorAddress=" + receivorAddress +
            ", source=" + source +
            ", remindDelive=" + remindDelive +
            ", rateType=" + rateType +
            ", buyerDelete=" + buyerDelete +
            ", arbitrateFee=" + arbitrateFee +
            ", arbitrateResult=" + arbitrateResult +
            ", refOrderId=" + refOrderId +
            ", attribute=" + attribute +
            ", poundageRate=" + poundageRate +
            ", startFreeTime=" + startFreeTime +
            ", endFreeTime=" + endFreeTime +
            ", createTime=" + createTime +
            ", creator=" + creator +
            ", updateTime=" + updateTime +
        "}";
    }
}
