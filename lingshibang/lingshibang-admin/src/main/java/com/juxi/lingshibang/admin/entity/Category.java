package com.juxi.lingshibang.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.util.List;

import com.juxi.lingshibang.common.model.BaseEntity;

/**
 * <p>
 * 服饰类目表
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
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

    @TableField(exist=false)
    private List<Category> categorys;

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

    public List<Category> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<Category> categorys) {
        this.categorys = categorys;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", parentId=" + parentId +
                ", categoryBackImage='" + categoryBackImage + '\'' +
                ", status=" + status +
                ", isRecommend=" + isRecommend +
                ", categorys=" + categorys +
                '}';
    }
}
