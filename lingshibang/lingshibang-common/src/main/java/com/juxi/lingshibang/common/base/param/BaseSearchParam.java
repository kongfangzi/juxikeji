package com.juxi.lingshibang.common.base.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: DaleShay
 * @Date: 2019/11/20 13:25
 * @Description:
 */
@Data
@ApiModel("搜索请求")
public class BaseSearchParam extends BaseQueryParam {

    private static final long serialVersionUID = -6351104134046213661L;

    @ApiModelProperty(value = "通用查询字段")
    private String searchField;

    @ApiModelProperty(value = "数据字段")
    private String searchKey;

    @ApiModelProperty(value = "排除数据关键字")
    private String ignoreKey;

    @ApiModelProperty(value = "包含数据")
    private List<String> containsValues;

    @ApiModelProperty(value = "排除数据")
    private List<String> ignoreValues;

}
