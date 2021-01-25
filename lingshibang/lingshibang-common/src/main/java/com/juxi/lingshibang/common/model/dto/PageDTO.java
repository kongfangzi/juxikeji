package com.juxi.lingshibang.common.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


/**
 * 分页对象实体
 * @param <T>
 * @author yks
 * @date 2020-03-10
 */
@Data
@NoArgsConstructor
public class PageDTO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    private Integer current;

    /**
     * 每页大小
     */
    private Integer size;

    /**
     * 数据总数
     */
    private Integer total;

    /**
     * 数据集合
     */
    private List<T> records;
}
