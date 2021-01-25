package com.juxi.lingshibang.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.juxi.lingshibang.common.model.BaseEntity;

/**
 * <p>
 * 类目组信息表
 * </p>
 *
 * @author liufeng
 * @since 2021-01-10
 */
@TableName("tb_category_group")
public class CategoryGroup extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 类目组id
     */
    @TableId(value = "group_id", type = IdType.AUTO)
    private Integer groupId;

    /**
     * 类目组名称
     */
    private String groupName;

    /**
     * 有效状态：0 无效 1 有效
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String createtor;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 修改人
     */
    private String updatetor;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public String getCreatetor() {
        return createtor;
    }

    public void setCreatetor(String createtor) {
        this.createtor = createtor;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    public String getUpdatetor() {
        return updatetor;
    }

    public void setUpdatetor(String updatetor) {
        this.updatetor = updatetor;
    }

    @Override
    public String toString() {
        return "CategoryGroup{" +
            "groupId=" + groupId +
            ", groupName=" + groupName +
            ", status=" + status +
            ", createTime=" + createTime +
            ", createtor=" + createtor +
            ", updateTime=" + updateTime +
            ", updatetor=" + updatetor +
        "}";
    }
}
