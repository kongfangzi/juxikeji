package com.juxi.lingshibang.admin.service;

import com.juxi.lingshibang.admin.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.juxi.lingshibang.admin.entity.SysMenu;
import com.juxi.lingshibang.admin.utilobject.vo.CategoryVO;
import com.juxi.lingshibang.admin.utilobject.vo.UserVO;
import com.juxi.lingshibang.common.base.Result;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 服饰类目表 服务类
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
public interface ICategoryService extends IService<Category> {

    @Transactional
    Result saveOrUpdate(@Valid @RequestBody CategoryVO categoryVO) throws Exception;

    /**
     * 根据父类目，查询子类目
     * @param parentId 父类目ID
     */
    List<Category> queryListParentId(Integer parentId);

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<Category> queryByParentId(Integer parentId);

    /**
     * 删除
     */
    Result delete(Integer id);
}
