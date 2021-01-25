package com.juxi.lingshibang.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.juxi.lingshibang.admin.entity.SysRoleUser;
import com.juxi.lingshibang.admin.entity.User;
import com.juxi.lingshibang.admin.mapper.SysRoleUserMapper;
import com.juxi.lingshibang.admin.mapper.UserMapper;
import com.juxi.lingshibang.admin.service.ISysRoleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juxi.lingshibang.admin.utilobject.dto.RoleUserDTO;
import com.juxi.lingshibang.common.enums.UserTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@Service
public class SysRoleUserServiceImpl extends ServiceImpl<SysRoleUserMapper, SysRoleUser> implements ISysRoleUserService {

    @Autowired
    private SysRoleUserMapper roleUserMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 给用户绑定角色
     * @param userId
     * @param roleIds
     */
    @Override
    public void addOrUpdateRoleUser(String userId, List<String> roleIds) {
        if(CollectionUtils.isEmpty(roleIds)){
            return ;
        }

        //先删除用户与角色关系
        deleteBatchByUserId(new String[] {userId});

        for(String roleId : roleIds){
            //保存多角色与用户关系
            SysRoleUser roleUser = new SysRoleUser();
            roleUser.setUserId(userId);
            roleUser.setRoleId(roleId);
            roleUserMapper.insert(roleUser);
        }
    }

    /**
     * 给角色绑定用户
     * @param roleUserDTO
     * @throws Exception
     */
    @Override
    public void addOrUpdateUsersToRole(RoleUserDTO roleUserDTO) throws Exception {

        if(roleUserDTO.getUserIds().size() == 0){
            return ;
        }
        List<String> userIds = roleUserDTO.getUserIds();
        //保存角色与多用户关系
        for(String userId : userIds){
            QueryWrapper<SysRoleUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("roleId", roleUserDTO.getRoleId());
            queryWrapper.eq("userId", userId);
            List<SysRoleUser> roleUsers = roleUserMapper.selectList(queryWrapper);
            if(roleUsers.size()>0){
                //删除角色与用户关系
                deleteByRoleUser(userId, roleUserDTO.getRoleId());
            }
            User user = userMapper.selectById(userId);
            user.setType(UserTypeEnum.ADMIN.getCode());
            userMapper.updateById(user);

            SysRoleUser roleUser = new SysRoleUser();
            roleUser.setUserId(user.getId());
            roleUser.setRoleId(roleUserDTO.getRoleId());
            roleUserMapper.insert(roleUser);
        }
    }

    @Override
    public int deleteBatchByUserId(String[] userId) {
        return roleUserMapper.deleteBatchByUserId(userId);
    }

    @Override
    public int deleteByRoleUser(String userId, String roleId) {
        return roleUserMapper.deleteByRoleUser(userId, roleId);
    }
}
