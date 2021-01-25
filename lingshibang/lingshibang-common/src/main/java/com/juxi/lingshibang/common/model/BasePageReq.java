package com.juxi.lingshibang.common.model;

import com.juxi.lingshibang.common.base.Constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页查询对象基类
 *
 * @author yks
 * @date 2019-12-31
 */
@Data
@ApiModel("分页请求")
public class BasePageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 模糊搜索
     */
    @ApiModelProperty("模糊搜索")
    private String searchText;

    /**
     * 当前页码
     */
    @ApiModelProperty("当前页码")
    private Integer current = 0;

    /**
     * 每页显示条数
     */
    @ApiModelProperty("每页条数")
    private Integer pageSize = 10;


    public Integer getPageSize() {
        return pageSize == null || pageSize < 1 ? Constants.DEFAULT_SIZE : pageSize;
    }

    public Integer getCurrent() {
        return current == null || current < 1 ? Constants.DEFAULT_CURRENT : current;
    }


}
