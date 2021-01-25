package com.juxi.lingshibang.common.base.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 公共实体类
 *
 * @author: Mr.Li yz
 * @create: 2019-10-16 17:10
 **/
@Data
@ApiModel("基础请求")
public class BaseParam implements Serializable {

    private static final long serialVersionUID = 5484191578658680358L;

    /**
     * 是否删除 0-否 1-是
     */
    @ApiModelProperty(value = "isDelete")
    private Integer isDelete;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人")
    private String creator;

    @ApiModelProperty(value = "更新人")
    private String updator;

    @ApiModelProperty(value = "创建开始时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTimeStart;
    @ApiModelProperty(value = "创建结束时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTimeEnd;
    @ApiModelProperty(value = "更新开始时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime updateTimeStart;
    @ApiModelProperty(value = "更新结束时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime updateTimeEnd;
}
