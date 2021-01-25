package com.juxi.lingshibang.common.base.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("排序属性")
public class SortParam implements Serializable {

    @ApiModelProperty(value = "排序类型")
    private String sortType;

    @ApiModelProperty(value = "排序字段")
    private String sortField;

}
