package com.juxi.lingshibang.admin.controller;


import com.alibaba.fastjson.JSONArray;
import com.juxi.lingshibang.admin.entity.SysPermission;
import com.juxi.lingshibang.admin.service.ISysPermissionService;
import com.juxi.lingshibang.admin.utilobject.dto.PermissionDTO;
import com.juxi.lingshibang.common.base.Result;
import com.juxi.lingshibang.common.enums.ObjectStatusEnum;
import com.juxi.lingshibang.common.model.dto.LoginUserDTO;
import com.juxi.lingshibang.common.util.ResponseUtil;
import com.juxi.lingshibang.common.util.StringUtil;
import com.juxi.lingshibang.common.util.login.CurrentUserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.juxi.lingshibang.common.base.BaseController;

import javax.validation.Valid;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@Validated
@RestController
@RequestMapping("/admin/sys-permission")
@Api(description = "权限管理")
public class SysPermissionController extends BaseController {

    @Autowired
    private ISysPermissionService permissionService;

    @Autowired
    private CurrentUserUtil currentUserUtil;


    @ApiOperation(value = "新增权限", httpMethod = "POST")
    @RequestMapping(value = "addOrUpdatePermission", method = RequestMethod.POST)
    public Result addOrUpdatePermission(@Valid @RequestBody PermissionDTO record) throws Exception {
        SysPermission permission = new SysPermission();
        BeanUtils.copyProperties(record, permission);
        if (StringUtil.isNotBlank(permission.getPermisId())) {
            permissionService.updateById(permission);
            return ResponseUtil.SUCCESS("更新数据成功", permission);
        }
        permission.setIsDelete(ObjectStatusEnum.NORMAL.getCode());
        permissionService.save(permission);
        return ResponseUtil.SUCCESS("新增数据成功", permission);
    }

    @ApiOperation(value = "根据用户ID查询用户权限", httpMethod = "POST")
    @RequestMapping(value = "getPermissionsByUserId", method = RequestMethod.POST)
    public Result getPermissionsByUserId(@RequestParam String userId, @RequestParam Integer device) throws Exception {
        JSONArray jsonArray = permissionService.getPermissionsByUserId(userId, device);
        return ResponseUtil.SUCCESS("获取数据成功", jsonArray);
    }

    @ApiOperation(value = "根据用户ID查询用户菜单权限", httpMethod = "POST")
    @RequestMapping(value = "getMenuIdsByUserId", method = RequestMethod.POST)
    public Result getMenuIdsByUserId(@RequestParam String userId, @RequestParam Integer device) throws Exception {
        String[] array = permissionService.getMenuIdsByUserId(userId, device);
        return ResponseUtil.SUCCESS("获取数据成功", array);
    }

    @ApiOperation(value = "根据端查询菜单权限", httpMethod = "GET")
    @GetMapping(value ="getPermissionsByDevice")
    public Result getPermissionsByDevice(@RequestParam Integer device){
        if(device==null){
            ResponseUtil.Failure("参数为空");
        }
        LoginUserDTO loginUserDTO=currentUserUtil.getUserInfo();
        if(loginUserDTO==null){
            ResponseUtil.Failure("获取用户信息失败");
        }
        JSONArray jsonArray=permissionService.getAllPermissions(loginUserDTO.getCompanyId(),device);
        return  ResponseUtil.SUCCESS("查询成功",jsonArray);
    }
}
