package com.juxi.lingshibang.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.juxi.lingshibang.common.model.BaseEntity;

/**
 * <p>
 * 服饰类目表
 * </p>
 *
 * @author liufeng
 * @since 2021-01-10
 */
@TableName("tb_category")
public class Category extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 类目id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类目名称
     */
    private String categoryName;

    /**
     * 父category_id
     */
    private Integer parentId;

    /**
     * 背景图片地址
     */
    private String categoryBackImage;

    /**
     * 有效状态：0 无效 1 有效
     */
    private Integer status;

    /**
     * 推荐分类0:不推荐,1:推荐
     */
    private Integer isRecommend;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 修改人
     */
    private String updator;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public String getCategoryBackImage() {
        return categoryBackImage;
    }

    public void setCategoryBackImage(String categoryBackImage) {
        this.categoryBackImage = categoryBackImage;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
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
    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    @Override
    public String toString() {
        return "Category{" +
            "id=" + id +
            ", categoryName=" + categoryName +
            ", parentId=" + parentId +
            ", categoryBackImage=" + categoryBackImage +
            ", status=" + status +
            ", isRecommend=" + isRecommend +
            ", createTime=" + createTime +
            ", creator=" + creator +
            ", updateTime=" + updateTime +
            ", updator=" + updator +
        "}";
    }
}
