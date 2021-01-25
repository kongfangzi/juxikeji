package com.juxi.lingshibang.admin.controller;


import com.juxi.lingshibang.admin.service.ISysRolePermissionService;
import com.juxi.lingshibang.admin.service.ISysRoleService;
import com.juxi.lingshibang.admin.utilobject.dto.RoleDTO;
import com.juxi.lingshibang.common.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.juxi.lingshibang.common.base.BaseController;

import javax.validation.Valid;
import java.util.Map;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@Validated
@RestController
@RequestMapping("/admin/sys-role")
@Api(description = "角色管理")
public class SysRoleController extends BaseController {

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysRolePermissionService rolePermissionService;

    @Transactional
    @ApiOperation(value = "新增|更新角色", httpMethod = "POST")
    @RequestMapping(value = "addOrUpdateRole", method = RequestMethod.POST)
    public Result addRole(@Valid @RequestBody RoleDTO record) throws Exception {
        return roleService.saveOrUpdate(record);
    }

    @ApiOperation(value = "逻辑删除角色", httpMethod = "POST")
    @RequestMapping(value = "delLogicRoles", method = RequestMethod.POST)
    public Result delLogicRoles(@RequestBody Map<String, String> params) throws Exception {
        String id = params.get("id");
        return roleService.delLogicListById(id);
    }
}
