package com.juxi.lingshibang.common.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @since 2019/7/12
 **/

@Data
@ApiModel(description = "分页查询基本模型")
public class PageQueryRequest {

    @ApiModelProperty(value = "当前页码")
    private Integer current = 1;

    @ApiModelProperty(value = "每页记录数")
    private Integer size = 50;

    @ApiModelProperty(value = "排序字段")
    private String sortBy;

    @ApiModelProperty(value = "排序规则")
    private String sortType;

}
