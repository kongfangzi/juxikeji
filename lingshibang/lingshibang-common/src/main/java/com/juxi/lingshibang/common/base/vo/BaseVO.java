package com.juxi.lingshibang.common.base.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 基础视图对象
 *
 * @author: Mr.Li yz
 * @create: 2019-10-16 17:10
 **/
@Data
@ApiModel("基础 VO")
public class BaseVO implements Serializable {

    private static final long serialVersionUID = -1695850022460957581L;


    @ApiModelProperty(value = "是否删除 0-否 1-是")
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


}
