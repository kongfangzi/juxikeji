package com.juxi.lingshibang.common.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 基础类
 * 实体基础类
 */
@Data
public abstract class BaseEntity implements Serializable {
    protected static final long serialVersionUID = 1L;

    /**
     * 是否删除 0-否 1-是
     */
    @TableField(value = "isDelete",fill = FieldFill.INSERT)
    private Integer isDelete;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "createTime",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "updateTime",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @TableField(value = "creator",fill = FieldFill.INSERT)
    private String creator;

    /**
     * 修改人
     */
    @TableField(value = "updator",fill = FieldFill.INSERT_UPDATE)
    private String updator;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
