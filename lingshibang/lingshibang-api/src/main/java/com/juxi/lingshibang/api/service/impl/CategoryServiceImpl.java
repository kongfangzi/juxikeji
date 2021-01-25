package com.juxi.lingshibang.api.service.impl;

import com.juxi.lingshibang.api.entity.Category;
import com.juxi.lingshibang.api.mapper.CategoryMapper;
import com.juxi.lingshibang.api.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服饰类目表 服务实现类
 * </p>
 *
 * @author liufeng
 * @since 2021-01-10
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
