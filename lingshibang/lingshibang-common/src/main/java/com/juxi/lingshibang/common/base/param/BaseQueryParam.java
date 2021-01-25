package com.juxi.lingshibang.common.base.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * 查询参数 分页查询
 *
 * @author: Mr.Li yz
 * @create: 2019-11-25 13:35
 **/
@Data
@ApiModel("分页请求")
public class BaseQueryParam extends BaseParam {
    private static final long serialVersionUID = 4351128308231352333L;
    /**
     * 当前
     */
    @ApiModelProperty(value = "当前页码",example = "1")
    private int current = 1;

    /**
     * 查询数量
     */
    @ApiModelProperty(value = "查询数量")
    private int size = 10;

    @ApiModelProperty(value = "排序字段")
    private String sortBy;

    @ApiModelProperty(value = "排序规则")
    private String sortType;

    @ApiModelProperty(value = "排序规则")
    private Map<String,String> sortParams;

    @ApiModelProperty(value = "查询条件")
    private Map<String,String> reqParams;
}
