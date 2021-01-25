package com.juxi.lingshibang.admin.controller;


import com.juxi.lingshibang.admin.entity.Category;
import com.juxi.lingshibang.admin.service.ICategoryService;
import com.juxi.lingshibang.admin.utilobject.vo.CategoryVO;
import com.juxi.lingshibang.admin.utilobject.vo.UserVO;
import com.juxi.lingshibang.common.base.Result;
import com.juxi.lingshibang.common.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.juxi.lingshibang.common.base.BaseController;

import java.util.List;

/**
 * <p>
 * 服饰类目表 前端控制器
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@RestController
@RequestMapping("/admin/category")
public class CategoryController extends BaseController {

    @Autowired
    private ICategoryService categoryService;

    @ApiOperation(value = "类目新增或者修改")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody CategoryVO categoryVO) throws Exception {
        return categoryService.saveOrUpdate(categoryVO);
    }

    @ApiOperation(value = "类目列表")
    @PostMapping("/queryListParentId")
    public Result queryListParentId(@RequestParam Integer parentId) throws Exception {
        List<Category> list = categoryService.queryListParentId(parentId);
        return ResponseUtil.SUCCESS("查询类目列表成功！", list);
    }

    @ApiOperation(value = "删除类目")
    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) throws Exception {
        categoryService.delete(id);
        return ResponseUtil.SUCCESS("删除类目成功！");
    }
}
