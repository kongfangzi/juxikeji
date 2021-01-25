package com.juxi.lingshibang.admin.controller;


import com.juxi.lingshibang.admin.common.annotation.SysLog;
import com.juxi.lingshibang.admin.common.utils.Constant;
import com.juxi.lingshibang.admin.entity.SysMenu;
import com.juxi.lingshibang.admin.service.ISysMenuService;
import com.juxi.lingshibang.admin.service.ISysMenuService;
import com.juxi.lingshibang.common.base.Result;
import com.juxi.lingshibang.common.exception.RRException;
import com.juxi.lingshibang.common.util.ResponseUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@RestController
@RequestMapping("/admin/sys-menu")
public class SysMenuController extends AbstractController {

    @Autowired
    private ISysMenuService sysMenuService;

    /**
     * 导航菜单
     */
    @RequestMapping("/nav")
    public Result nav(){
        List<SysMenu> menuList = sysMenuService.getUserMenuList(getUserId());
        return ResponseUtil.SUCCESS("menuList", menuList);
    }

    /**
     * 所有菜单列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:menu:list")
    public List<SysMenu> list(){
        List<SysMenu> menuList = sysMenuService.list();
        for(SysMenu sysMenu : menuList){
            SysMenu parentMenu = sysMenuService.getById(sysMenu.getParentId());
            if(parentMenu != null){
                sysMenu.setParentName(parentMenu.getMenuName());
            }
        }

        return menuList;
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @RequestMapping("/select")
    @RequiresPermissions("sys:menu:select")
    public Result select(){
        //查询列表数据
        List<SysMenu> menuList = sysMenuService.queryNotButtonList();

        //添加顶级菜单
        SysMenu root = new SysMenu();
        root.setMenuId(0L);
        root.setMenuName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);

        return ResponseUtil.SUCCESS("menuList", menuList);
    }

    /**
     * 菜单信息
     */
    @RequestMapping("/info/{menuId}")
    @RequiresPermissions("sys:menu:info")
    public Result info(@PathVariable("menuId") Long menuId){
        SysMenu menu = sysMenuService.getById(menuId);
        return ResponseUtil.SUCCESS("menu", menu);
    }

    /**
     * 保存
     */
    @SysLog("保存菜单")
    @RequestMapping("/save")
    @RequiresPermissions("sys:menu:save")
    public Result save(@RequestBody SysMenu menu){
        //数据校验
        verifyForm(menu);

        sysMenuService.save(menu);

        return ResponseUtil.SUCCESS("保存菜单成功");
    }

    /**
     * 修改
     */
    @SysLog("修改菜单")
    @RequestMapping("/update")
    @RequiresPermissions("sys:menu:update")
    public Result update(@RequestBody SysMenu menu){
        //数据校验
        verifyForm(menu);

        sysMenuService.updateById(menu);

        return ResponseUtil.SUCCESS("修改菜单成功");
    }

    /**
     * 删除
     */
    @SysLog("删除菜单")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:menu:delete")
    public Result delete(long menuId){
        if(menuId <= 31){
            return ResponseUtil.Failure("系统菜单，不能删除");
        }

        //判断是否有子菜单或按钮
        List<SysMenu> menuList = sysMenuService.queryListParentId(menuId);
        if(!menuList.isEmpty()){
            return ResponseUtil.Failure("请先删除子菜单或按钮");
        }

        sysMenuService.delete(menuId);

        return ResponseUtil.SUCCESS("删除菜单成功");
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysMenu menu){
        if(StringUtils.isBlank(menu.getMenuName())){
            throw new RRException("菜单名称不能为空");
        }

        if(menu.getParentId() == null){
            throw new RRException("上级菜单不能为空");
        }

        //菜单
        if(menu.getMenuType() == Constant.MenuType.MENU.getValue()&&StringUtils.isBlank(menu.getUrl())){
            throw new RRException("菜单URL不能为空");
        }

        //上级菜单类型
        int parentType = Constant.MenuType.CATALOG.getValue();
        if(menu.getParentId() != 0){
            SysMenu parentMenu = sysMenuService.getById(menu.getParentId());
            parentType = parentMenu.getMenuType();
        }

        //目录、菜单
        if(menu.getMenuType() == Constant.MenuType.CATALOG.getValue() ||
                menu.getMenuType() == Constant.MenuType.MENU.getValue()){
            if(parentType != Constant.MenuType.CATALOG.getValue()){
                throw new RRException("上级菜单只能为目录类型");
            }
            return ;
        }

        //按钮
        if(menu.getMenuType() == Constant.MenuType.BUTTON.getValue()){
            if(parentType != Constant.MenuType.MENU.getValue()){
                throw new RRException("上级菜单只能为菜单类型");
            }
            return ;
        }
    }
}
