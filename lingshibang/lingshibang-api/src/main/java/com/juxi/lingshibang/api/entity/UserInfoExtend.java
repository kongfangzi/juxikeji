package com.juxi.lingshibang.api.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.juxi.lingshibang.common.model.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author liufeng
 * @since 2021-01-10
 */
@TableName("tb_user_info_extend")
public class UserInfoExtend extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户编号
     */
    private String userCode;

    /**
     * 微信号
     */
    private String wxId;

    /**
     * 邮件
     */
    private String email;

    /**
     * 地址
     */
    private String address;

    /**
     * 购买力低价
     */
    private BigDecimal buyLevelLow;

    /**
     * 购买力高价
     */
    private BigDecimal buyLevelHigh;

    /**
     * 用户标签
     */
    private String userTags;

    /**
     * 备注
     */
    private String memo;

    /**
     * 购买商品数
     */
    private Integer orderGoodsNum;

    /**
     * 购买商品均价
     */
    private BigDecimal orderAveragePrice;

    /**
     * 最后登录ip地址
     */
    private String lastLoadIp;

    /**
     * 总交易金额
     */
    private BigDecimal tradingMoney;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoadTime;

    /**
     * 微信二维码图片
     */
    private String wecharImg;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;

    /**
     * 更新时间
     */
    private LocalDateTime updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public BigDecimal getBuyLevelLow() {
        return buyLevelLow;
    }

    public void setBuyLevelLow(BigDecimal buyLevelLow) {
        this.buyLevelLow = buyLevelLow;
    }
    public BigDecimal getBuyLevelHigh() {
        return buyLevelHigh;
    }

    public void setBuyLevelHigh(BigDecimal buyLevelHigh) {
        this.buyLevelHigh = buyLevelHigh;
    }
    public String getUserTags() {
        return userTags;
    }

    public void setUserTags(String userTags) {
        this.userTags = userTags;
    }
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
    public Integer getOrderGoodsNum() {
        return orderGoodsNum;
    }

    public void setOrderGoodsNum(Integer orderGoodsNum) {
        this.orderGoodsNum = orderGoodsNum;
    }
    public BigDecimal getOrderAveragePrice() {
        return orderAveragePrice;
    }

    public void setOrderAveragePrice(BigDecimal orderAveragePrice) {
        this.orderAveragePrice = orderAveragePrice;
    }
    public String getLastLoadIp() {
        return lastLoadIp;
    }

    public void setLastLoadIp(String lastLoadIp) {
        this.lastLoadIp = lastLoadIp;
    }
    public BigDecimal getTradingMoney() {
        return tradingMoney;
    }

    public void setTradingMoney(BigDecimal tradingMoney) {
        this.tradingMoney = tradingMoney;
    }
    public LocalDateTime getLastLoadTime() {
        return lastLoadTime;
    }

    public void setLastLoadTime(LocalDateTime lastLoadTime) {
        this.lastLoadTime = lastLoadTime;
    }
    public String getWecharImg() {
        return wecharImg;
    }

    public void setWecharImg(String wecharImg) {
        this.wecharImg = wecharImg;
    }
    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }
    public LocalDateTime getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(LocalDateTime updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "UserInfoExtend{" +
            "id=" + id +
            ", userCode=" + userCode +
            ", wxId=" + wxId +
            ", email=" + email +
            ", address=" + address +
            ", buyLevelLow=" + buyLevelLow +
            ", buyLevelHigh=" + buyLevelHigh +
            ", userTags=" + userTags +
            ", memo=" + memo +
            ", orderGoodsNum=" + orderGoodsNum +
            ", orderAveragePrice=" + orderAveragePrice +
            ", lastLoadIp=" + lastLoadIp +
            ", tradingMoney=" + tradingMoney +
            ", lastLoadTime=" + lastLoadTime +
            ", wecharImg=" + wecharImg +
            ", createtime=" + createtime +
            ", updatetime=" + updatetime +
        "}";
    }
}
