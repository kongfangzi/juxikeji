package com.juxi.lingshibang.common.base.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ConditionParam implements Serializable {
    @ApiModelProperty(value = "数据字段")
    private String searchKey;

    @ApiModelProperty(value = "包含数据")
    private List<String> containsValues;

    @ApiModelProperty(value = "排除数据")
    private List<String> ignoreValues;
}
