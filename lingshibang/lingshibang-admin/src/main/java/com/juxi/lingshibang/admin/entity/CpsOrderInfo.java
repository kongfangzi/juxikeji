package com.juxi.lingshibang.admin.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.juxi.lingshibang.common.model.BaseEntity;

/**
 * <p>
 * 订单cps分成信息表
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@TableName("tb_cps_order_info")
public class CpsOrderInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单编码
     */
    private String orderCode;

    /**
     * 订单金额
     */
    private BigDecimal totalPrice;

    /**
     * 本单总利润
     */
    private BigDecimal orderProfit;

    /**
     * 订单cps状态：1:付款 2:成交; 3:关闭 4:结算
     */
    private Integer orderStatus;

    /**
     * 订单类型：
     */
    private Integer orderType;

    /**
     * cps分成类型：1公众号运营推广分成，2个人分享推广分成，3机构推广分成，4第三方平台推广分成
     */
    private Integer cpsType;

    /**
     * cps分成主体ID
     */
    private Integer cpsSubjectId;

    /**
     * cps分成比例
     */
    private BigDecimal cpsProfitRate;

    /**
     * 订单支付时间
     */
    private LocalDateTime payTime;

    /**
     * 订单成交（收货）时间
     */
    private LocalDateTime finishTime;

    /**
     * cps结算时间
     */
    private LocalDateTime settlementTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
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
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    public BigDecimal getOrderProfit() {
        return orderProfit;
    }

    public void setOrderProfit(BigDecimal orderProfit) {
        this.orderProfit = orderProfit;
    }
    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
    public Integer getCpsType() {
        return cpsType;
    }

    public void setCpsType(Integer cpsType) {
        this.cpsType = cpsType;
    }
    public Integer getCpsSubjectId() {
        return cpsSubjectId;
    }

    public void setCpsSubjectId(Integer cpsSubjectId) {
        this.cpsSubjectId = cpsSubjectId;
    }
    public BigDecimal getCpsProfitRate() {
        return cpsProfitRate;
    }

    public void setCpsProfitRate(BigDecimal cpsProfitRate) {
        this.cpsProfitRate = cpsProfitRate;
    }
    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }
    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }
    public LocalDateTime getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(LocalDateTime settlementTime) {
        this.settlementTime = settlementTime;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CpsOrderInfo{" +
            "id=" + id +
            ", orderCode=" + orderCode +
            ", totalPrice=" + totalPrice +
            ", orderProfit=" + orderProfit +
            ", orderStatus=" + orderStatus +
            ", orderType=" + orderType +
            ", cpsType=" + cpsType +
            ", cpsSubjectId=" + cpsSubjectId +
            ", cpsProfitRate=" + cpsProfitRate +
            ", payTime=" + payTime +
            ", finishTime=" + finishTime +
            ", settlementTime=" + settlementTime +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
