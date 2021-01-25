package com.juxi.lingshibang.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.juxi.lingshibang.common.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
public class SysPermission extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "permis_id", type = IdType.ID_WORKER_STR)
    private String permisId;

    /**
     * 父ID
     */
    @TableField("parentId")
    private String parentId;

    /**
     * 资源名
     */
    private String name;

    /**
     * 样式
     */
    private String css;

    /**
     * 访问连接
     */
    private String href;

    /**
     * 类型
     */
    private Boolean type;

    /**
     * 权限
     */
    private String permission;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 修改时间
     */
    private LocalDateTime updatetime;

    /**
     * 是否删除 0-否 1-是
     */
    private Boolean removed;

    /**
     * 删除时间
     */
    private LocalDateTime removedtime;

    public String getPermisId() {
        return permisId;
    }

    public void setPermisId(String permisId) {
        this.permisId = permisId;
    }
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }
    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
    public LocalDateTime getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(LocalDateTime updatetime) {
        this.updatetime = updatetime;
    }
    public Boolean getRemoved() {
        return removed;
    }

    public void setRemoved(Boolean removed) {
        this.removed = removed;
    }
    public LocalDateTime getRemovedtime() {
        return removedtime;
    }

    public void setRemovedtime(LocalDateTime removedtime) {
        this.removedtime = removedtime;
    }

    @Override
    public String toString() {
        return "SysPermission{" +
            "permisId=" + permisId +
            ", parentId=" + parentId +
            ", name=" + name +
            ", css=" + css +
            ", href=" + href +
            ", type=" + type +
            ", permission=" + permission +
            ", sort=" + sort +
            ", creator=" + creator +
            ", createtime=" + createtime +
            ", modifier=" + modifier +
            ", updatetime=" + updatetime +
            ", removed=" + removed +
            ", removedtime=" + removedtime +
        "}";
    }
}
