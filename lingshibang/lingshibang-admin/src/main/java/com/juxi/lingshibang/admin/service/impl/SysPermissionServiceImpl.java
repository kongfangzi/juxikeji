package com.juxi.lingshibang.admin.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.juxi.lingshibang.admin.entity.SysPermission;
import com.juxi.lingshibang.admin.mapper.SysPermissionMapper;
import com.juxi.lingshibang.admin.service.ISysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juxi.lingshibang.common.enums.ObjectStatusEnum;
import com.juxi.lingshibang.common.enums.PermissionTypeEnum;
import com.juxi.lingshibang.common.model.dto.UserPermissionDTO;
import com.juxi.lingshibang.common.redis.RedisUtil;
import com.juxi.lingshibang.common.util.StringUtil;
import com.juxi.lingshibang.common.util.login.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private  SysPermissionMapper permissionMapper;

    /**
     * 根据用户ID查询用户权限
     * @param userId
     * @return
     */
    @Override
    public JSONArray getPermissionsByUserId(String userId, Integer device) {
        String permissions= RedisUtil.getObject(stringRedisTemplate, LoginUtil.getRedisCacheUserPermissionKey(userId));
        List<UserPermissionDTO> permissionDTOList=null;
        if(!StringUtil.isNotBlank(permissions)){
            permissionDTOList=permissionMapper.listByUserId(userId,device,
                    null,
                    ObjectStatusEnum.NORMAL.getCode());
        }else{
            permissionDTOList= JSONObject.parseArray(permissions,UserPermissionDTO.class);
        }
        JSONArray permissionArray = new JSONArray();
        setPermissionsTree("0",permissionDTOList,permissionArray);
        return permissionArray;
    }

    /**
     * 根据用户ID查询用户菜单权限
     * @param userId
     * @param device
     * @return
     */
    @Override
    public String[] getMenuIdsByUserId(String userId, Integer device) {
        List<UserPermissionDTO> permissionDTOList=permissionMapper.listByUserId(userId,device,
                PermissionTypeEnum.MENU.getCode(),
                ObjectStatusEnum.NORMAL.getCode());
        if(!CollectionUtils.isEmpty(permissionDTOList)){
            String[] menuIds=new String[permissionDTOList.size()];
            int i=0;
            for(UserPermissionDTO userPermissionDTO:permissionDTOList){
                menuIds[i++]=userPermissionDTO.getId();
            }
            return  menuIds;
        }
        return null;
    }

    /**
     * 根据企业ID查询权限
     * @param companyId
     * @param device
     * @return
     */
    @Override
    public JSONArray getAllPermissions(String companyId,Integer device) {
        List<UserPermissionDTO> permissionDTOList=permissionMapper.
                listByCompanyId(companyId,
                        device,
                        null,
                        ObjectStatusEnum.NORMAL.getCode());
        JSONArray permissionArray = new JSONArray();
        setPermissionsTree("0",permissionDTOList,permissionArray);
        return permissionArray;
    }

    /**
     * 设置子元素
     * @param p
     * @param permissions
     */
    private void setChild(UserPermissionDTO p, List<UserPermissionDTO> permissions) {
        List<UserPermissionDTO> child = permissions.parallelStream().filter(
                a -> a.getParentId().equals(p.getId())).collect(Collectors.toList());
        p.setChildren(child);
        if (!CollectionUtils.isEmpty(child)) {
            child.parallelStream().forEach(c -> {
                //递归设置子元素，多级菜单支持
                setChild(c, permissions);
            });
        }
    }

    /**
     * 菜单列表
     * @param pId
     * @param permissionsAll
     * @param list
     */
    private void setPermissionsList(String pId,
                                    List<UserPermissionDTO> permissionsAll,
                                    List<UserPermissionDTO> list) {
        for (UserPermissionDTO per : permissionsAll) {
            if (per.getParentId().equals(pId)) {
                list.add(per);
                if (permissionsAll.stream().filter(
                        p -> p.getParentId().equals(per.getId())).findAny() != null) {
                    setPermissionsList(per.getId(), permissionsAll, list);
                }
            }
        }
    }

    /**
     * 菜单树
     * @param pId
     * @param permissionsAll
     * @param array
     */
    private void setPermissionsTree(String pId, List<UserPermissionDTO> permissionsAll, JSONArray array) {
        for (UserPermissionDTO per : permissionsAll) {
            if (per.getParentId().equals(pId)) {
                String string = JSONObject.toJSONString(per);
                JSONObject parent = (JSONObject) JSONObject.parse(string);
                array.add(parent);
                if (permissionsAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny() != null) {
                    JSONArray child = new JSONArray();
                    parent.put("children", child);
                    setPermissionsTree(per.getId(), permissionsAll, child);
                }
            }
        }
    }
}
