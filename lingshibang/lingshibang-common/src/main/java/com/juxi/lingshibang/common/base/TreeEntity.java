package com.juxi.lingshibang.common.base;

import java.util.List;

/**
 * 多级菜单
 * @author xiaoxiong
 */
public interface TreeEntity<T> {

    /**
     * id
     * @return
     */
    Integer getId();

    /**
     * 父id
     *
     * @return
     */
    Integer getPid();

    /**
     * 子菜单
     * @param list
     */
    void setSubmenu(List<T> list);
}
