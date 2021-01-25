package com.juxi.lingshibang.api.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.juxi.lingshibang.common.model.BaseEntity;

/**
 * <p>
 * 订单退款信息表
 * </p>
 *
 * @author liufeng
 * @since 2021-01-10
 */
@TableName("tb_order_return")
public class OrderReturn extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 退单编号
     */
    private String returnCode;

    /**
     * 订单编码
     */
    private String orderCode;

    /**
     * 退单类型：0未发货退款，1已发货、未签收退款，2已签收退款
     */
    private Integer type;

    /**
     * 订单价格
     */
    private BigDecimal totalPrice;

    /**
     * 奖励金额
     */
    private BigDecimal rewardMoney;

    /**
     * 卖家用户编码
     */
    private String sellerCode;

    /**
     * 买家用户编码
     */
    private String buyerCode;

    /**
     * 退款原因
     */
    private String reason;

    /**
     * 退款说明
     */
    private String message;

    /**
     * 退款审核状态：0初始，1同意申请，2退款完成，3拒绝，4取消，5支付平台原路退款系统发起
     */
    private Integer auditStatus;

    /**
     * 支付类型：0微信，1支付宝，2银联
     */
    private Integer payType;

    /**
     * 支付平台返回id
     */
    private String paymentId;

    /**
     * 退款状态：0初始，1打款中，2成功，3失败
     */
    private Integer refundPaymentStatus;

    /**
     * 支付平台退款返回id
     */
    private String refundPaymentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    public BigDecimal getRewardMoney() {
        return rewardMoney;
    }

    public void setRewardMoney(BigDecimal rewardMoney) {
        this.rewardMoney = rewardMoney;
    }
    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }
    public String getBuyerCode() {
        return buyerCode;
    }

    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }
    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    public Integer getRefundPaymentStatus() {
        return refundPaymentStatus;
    }

    public void setRefundPaymentStatus(Integer refundPaymentStatus) {
        this.refundPaymentStatus = refundPaymentStatus;
    }
    public String getRefundPaymentId() {
        return refundPaymentId;
    }

    public void setRefundPaymentId(String refundPaymentId) {
        this.refundPaymentId = refundPaymentId;
    }

    @Override
    public String toString() {
        return "OrderReturn{" +
            "id=" + id +
            ", returnCode=" + returnCode +
            ", orderCode=" + orderCode +
            ", type=" + type +
            ", totalPrice=" + totalPrice +
            ", rewardMoney=" + rewardMoney +
            ", sellerCode=" + sellerCode +
            ", buyerCode=" + buyerCode +
            ", reason=" + reason +
            ", message=" + message +
            ", auditStatus=" + auditStatus +
            ", payType=" + payType +
            ", paymentId=" + paymentId +
            ", refundPaymentStatus=" + refundPaymentStatus +
            ", refundPaymentId=" + refundPaymentId +
        "}";
    }
}
