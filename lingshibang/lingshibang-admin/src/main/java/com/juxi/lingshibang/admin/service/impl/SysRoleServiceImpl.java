package com.juxi.lingshibang.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.juxi.lingshibang.admin.entity.SysRole;
import com.juxi.lingshibang.admin.entity.SysRoleUser;
import com.juxi.lingshibang.admin.entity.User;
import com.juxi.lingshibang.admin.mapper.SysRoleMapper;
import com.juxi.lingshibang.admin.mapper.UserMapper;
import com.juxi.lingshibang.admin.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juxi.lingshibang.admin.service.ISysRoleUserService;
import com.juxi.lingshibang.admin.utilobject.dto.RoleDTO;
import com.juxi.lingshibang.common.base.Result;
import com.juxi.lingshibang.common.enums.ObjectStatusEnum;
import com.juxi.lingshibang.common.util.ResponseUtil;
import com.juxi.lingshibang.common.util.StringUtil;
import com.juxi.lingshibang.common.util.login.CurrentUserUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private CurrentUserUtil currentUserUtil;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ISysRoleUserService roleUserService;

    @Override
    public Result<SysRole> saveOrUpdate(RoleDTO record) {
        String companyId = currentUserUtil.getUserInfo().getCompanyId();
        SysRole role = new SysRole();
        BeanUtils.copyProperties(record, role);
        if (StringUtil.isNotBlank(role.getRoleId().toString())) {
            //角色名唯一性判断
            QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("company_id", companyId);
            queryWrapper.eq("name", role.getRoleName());
            queryWrapper.eq("is_delete", ObjectStatusEnum.NORMAL.getCode());
            List<SysRole> roles = roleMapper.selectList(queryWrapper);
            SysRole r = roleMapper.selectById(role.getRoleId());
            roles.remove(r);
            if (roles.size() > 0) {
                return ResponseUtil.Failure("角色名不可重复！");
            }
            //更新角色
            roleMapper.updateById(role);
            return ResponseUtil.SUCCESS("更新数据成功", role);
        }

        //角色名唯一性判断
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("company_id", companyId);
        queryWrapper.eq("name", role.getRoleName());
        queryWrapper.eq("is_delete", ObjectStatusEnum.NORMAL.getCode());
        List<SysRole> roles = roleMapper.selectList(queryWrapper);
        if(roles.size() > 0){
            return ResponseUtil.Failure("角色名不可重复！");
        }
        //新增角色
        role.setRoleId(IdWorker.getIdStr());
        roleMapper.insert(role);
        return ResponseUtil.SUCCESS("新增数据成功",role);
    }

    @Override
    public List<SysRole> getListByIds(List<String> ids) {
        List<SysRole> roles = roleMapper.getRoleByIds(ids);
        return roles;
    }

    @Override
    public Result delLogicListById(String id) {
        SysRole role = roleMapper.selectById(id);
        if(null != role) {
            QueryWrapper<SysRoleUser> query = new QueryWrapper<>();
            query.eq("roleId", id);
            List<SysRoleUser> roleUsers = roleUserService.list(query);
            if(roleUsers.size() > 0){
                return ResponseUtil.Failure("请先删除角色绑定的用户！");
            }
            role.setIsDelete(ObjectStatusEnum.DELETE.getCode());
            roleMapper.updateById(role);
            return ResponseUtil.SUCCESS("逻辑删除角色成功", id);
        }

        List<String> roleIds = new ArrayList<>();
        //判断角色组下角色是否有用户
        List<User> users = userMapper.getUsersByRoleIds(roleIds);
        if(users.size() > 0) {
            return ResponseUtil.Failure("请先删除角色绑定的用户！");
        }
        LambdaQueryWrapper<SysRole> lamRole = new LambdaQueryWrapper();
        lamRole.in(!CollectionUtils.isEmpty(roleIds),SysRole::getRoleId,roleIds);
        SysRole ro = new SysRole();
        ro.setIsDelete(ObjectStatusEnum.DELETE.getCode());
        roleMapper.update(ro, lamRole);
        return ResponseUtil.SUCCESS("逻辑删除角色组下所有角色成功", id + roleIds);
    }


}
