package com.juxi.lingshibang.admin.service.impl;

import com.juxi.lingshibang.admin.entity.SysRoleMenu;
import com.juxi.lingshibang.admin.mapper.SysRoleMenuMapper;
import com.juxi.lingshibang.admin.service.ISysRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

    @Override
    public void saveOrUpdate(String roleId, List<String> menuIdList) {

        //先删除角色与菜单关系
        deleteBatch(new String[]{roleId});

        if(menuIdList.size() == 0){
            return ;
        }

        //保存角色与菜单关系
        for(String menuId : menuIdList){
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(roleId);

            this.save(roleMenu);
        }
    }

    @Override
    public List<String> queryMenuIdList(String roleId) {
        return null;
    }

    @Override
    public int deleteBatch(String[] roleIds) {
        return 0;
    }
}
