package com.juxi.lingshibang.api.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.juxi.lingshibang.common.model.BaseEntity;

/**
 * <p>
 * 用户行为数据记录表
 * </p>
 *
 * @author liufeng
 * @since 2021-01-10
 */
@TableName("tb_user_behavior_data")
public class UserBehaviorData extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户编号
     */
    private String userCode;

    /**
     * 行为类型 1：浏览 2:咨询；3：加购物车；4：收藏；5：下单；6：支付； 7：搜索
     */
    private Integer behaviorType;

    /**
     * 商品编码
     */
    private String goodsSn;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 商品类型
     */
    private Integer goodsType;

    /**
     * 类目
     */
    private Integer categoryId;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 搜索关键词
     */
    private String searchKeyWord;

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
    public Integer getBehaviorType() {
        return behaviorType;
    }

    public void setBehaviorType(Integer behaviorType) {
        this.behaviorType = behaviorType;
    }
    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getSearchKeyWord() {
        return searchKeyWord;
    }

    public void setSearchKeyWord(String searchKeyWord) {
        this.searchKeyWord = searchKeyWord;
    }

    @Override
    public String toString() {
        return "UserBehaviorData{" +
            "id=" + id +
            ", userCode=" + userCode +
            ", behaviorType=" + behaviorType +
            ", goodsSn=" + goodsSn +
            ", orderId=" + orderId +
            ", goodsType=" + goodsType +
            ", categoryId=" + categoryId +
            ", price=" + price +
            ", searchKeyWord=" + searchKeyWord +
        "}";
    }
}
