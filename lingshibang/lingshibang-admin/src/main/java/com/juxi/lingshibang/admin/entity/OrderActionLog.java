package com.juxi.lingshibang.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.juxi.lingshibang.common.model.BaseEntity;

/**
 * <p>
 * 订单操作日志
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@TableName("tb_order_action_log")
public class OrderActionLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 操作用户编号
     */
    private String actionUserCode;

    /**
     * 订单的状态： 1确认 2已取消 3无效
     */
    private Integer orderStatus;

    /**
     * 商品配送情况; 0未发货, 1已发货, 2已收货, 4退货
     */
    private Integer shippingStatus;

    /**
     * 支付状态; 0未付款; 1付款中; 2已付款
     */
    private Integer payStatus;

    /**
     * 操作终端：0小程序，1 App，2 管理后台
     */
    private Integer actionTerminal;

    /**
     * 操作注释
     */
    private String actionNote;

    /**
     * 实现状态：0失败，1成功
     */
    private Integer securedStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

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
    public String getActionUserCode() {
        return actionUserCode;
    }

    public void setActionUserCode(String actionUserCode) {
        this.actionUserCode = actionUserCode;
    }
    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
    public Integer getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(Integer shippingStatus) {
        this.shippingStatus = shippingStatus;
    }
    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }
    public Integer getActionTerminal() {
        return actionTerminal;
    }

    public void setActionTerminal(Integer actionTerminal) {
        this.actionTerminal = actionTerminal;
    }
    public String getActionNote() {
        return actionNote;
    }

    public void setActionNote(String actionNote) {
        this.actionNote = actionNote;
    }
    public Integer getSecuredStatus() {
        return securedStatus;
    }

    public void setSecuredStatus(Integer securedStatus) {
        this.securedStatus = securedStatus;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "OrderActionLog{" +
            "id=" + id +
            ", orderCode=" + orderCode +
            ", actionUserCode=" + actionUserCode +
            ", orderStatus=" + orderStatus +
            ", shippingStatus=" + shippingStatus +
            ", payStatus=" + payStatus +
            ", actionTerminal=" + actionTerminal +
            ", actionNote=" + actionNote +
            ", securedStatus=" + securedStatus +
            ", createTime=" + createTime +
        "}";
    }
}
