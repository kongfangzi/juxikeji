package com.juxi.lingshibang.admin.mapper;

import com.juxi.lingshibang.admin.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 服饰类目表 Mapper 接口
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 根据父类目，查询子类目
     * @param parentId 父类目ID
     */
    List<Category> queryByParentId(Integer parentId);
}
