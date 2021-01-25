package com.juxi.lingshibang.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.juxi.lingshibang.common.model.BaseEntity;

/**
 * <p>
 * 用户粉丝表和关注表
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@TableName("tb_follows")
public class Follows extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 被关注者
     */
    private String userCode;

    /**
     * 粉丝用户编码
     */
    private String fansUserCode;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;

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
    public String getFansUserCode() {
        return fansUserCode;
    }

    public void setFansUserCode(String fansUserCode) {
        this.fansUserCode = fansUserCode;
    }
    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "Follows{" +
            "id=" + id +
            ", userCode=" + userCode +
            ", fansUserCode=" + fansUserCode +
            ", createtime=" + createtime +
        "}";
    }
}
