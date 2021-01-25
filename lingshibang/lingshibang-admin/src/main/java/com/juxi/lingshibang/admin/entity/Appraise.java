package com.juxi.lingshibang.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.juxi.lingshibang.common.model.BaseEntity;

/**
 * <p>
 * 评价信息表
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@TableName("tb_appraise")
public class Appraise extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 评价用户编号
     */
    private String userCode;

    /**
     * 回复用户编号
     */
    private String replyUserCode;

    /**
     * 评价内容
     */
    private String content;

    /**
     * 评价回复内容
     */
    private String replyContent;

    /**
     * 评价时间
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
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public String getReplyUserCode() {
        return replyUserCode;
    }

    public void setReplyUserCode(String replyUserCode) {
        this.replyUserCode = replyUserCode;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
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
        return "Appraise{" +
            "id=" + id +
            ", orderId=" + orderId +
            ", userCode=" + userCode +
            ", replyUserCode=" + replyUserCode +
            ", content=" + content +
            ", replyContent=" + replyContent +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
