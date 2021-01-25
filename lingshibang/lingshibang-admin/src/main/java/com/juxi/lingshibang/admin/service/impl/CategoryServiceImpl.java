package com.juxi.lingshibang.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.juxi.lingshibang.admin.entity.Category;
import com.juxi.lingshibang.admin.mapper.CategoryMapper;
import com.juxi.lingshibang.admin.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juxi.lingshibang.admin.utilobject.vo.CategoryVO;
import com.juxi.lingshibang.admin.utils.response.Response;
import com.juxi.lingshibang.common.base.Result;
import com.juxi.lingshibang.common.util.ResponseUtil;
import com.juxi.lingshibang.common.util.login.CurrentUserUtil;
import com.juxi.lingshibang.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服饰类目表 服务实现类
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired(required=true)
    private CurrentUserUtil currentUserUtil;

    @Override
    public Result saveOrUpdate(@Valid CategoryVO categoryVO) throws Exception {
        Category category = new Category();
        BeanUtils.copyProperties(categoryVO, category);
        if (StringUtils.isNotEmpty(String.valueOf(category.getId())) && null != categoryMapper.selectById(category.getId())) {
            //更新
            category.setUpdateTime(LocalDateTime.now());
            category.setUpdator(currentUserUtil.getUserInfo().getUsername());
            categoryMapper.updateById(category);
            return ResponseUtil.SUCCESS("类目更新成功！", category);
        }
        //新增
        category.setCreateTime(LocalDateTime.now());
        category.setCreator(currentUserUtil.getUserInfo().getUsername());
        categoryMapper.insert(category);
        return ResponseUtil.SUCCESS("类目新增成功！", category);
    }

    @Override
    public List<Category> queryListParentId(Integer parentId) {
        List<Category> categoryList = queryByParentId(parentId);
        if(!CollectionUtils.isEmpty(categoryList)){
            return categoryList;
        }

        List<Category> list = new ArrayList<Category>();
        for(Category category : categoryList){
            if(categoryList.contains(category.getId())){
                list.add(category);
            }
        }
        return list;
    }

    @Override
    public List<Category> queryByParentId(Integer parentId) {
        return baseMapper.queryByParentId(parentId);
    }

    @Override
    public Result delete(Integer id) {
        if (StringUtils.isNotEmpty(String.valueOf(id)) && null != categoryMapper.selectById(id)) {
            List<Category> categoryList = queryListParentId(id);
            if(!CollectionUtils.isEmpty(categoryList)){
                return ResponseUtil.Failure("该类目下有子类目，不能删除！", categoryList);
            }
            categoryMapper.deleteById(id);
            return ResponseUtil.SUCCESS("类目删除成功！");
        }
        return ResponseUtil.Failure("参数错误！", id);
    }
}
