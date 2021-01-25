package com.juxi.lingshibang.admin.controller;


import com.alibaba.fastjson.JSONArray;
import com.juxi.lingshibang.admin.entity.User;
import com.juxi.lingshibang.admin.service.ISysPermissionService;
import com.juxi.lingshibang.admin.service.IUserService;
import com.juxi.lingshibang.admin.utilobject.dto.LoginDTO;
import com.juxi.lingshibang.admin.utilobject.vo.UserVO;
import com.juxi.lingshibang.common.base.Result;
import com.juxi.lingshibang.common.base.enums.CommonCodeEnum;
import com.juxi.lingshibang.common.enums.DeviceEnum;
import com.juxi.lingshibang.common.enums.ModuleEnum;
import com.juxi.lingshibang.common.enums.UserTypeEnum;
import com.juxi.lingshibang.common.log.OpLogAnnotation;
import com.juxi.lingshibang.common.model.dto.LoginUserDTO;
import com.juxi.lingshibang.common.util.JwtUtil;
import com.juxi.lingshibang.common.util.ListUtil;
import com.juxi.lingshibang.common.util.ResponseUtil;
import com.juxi.lingshibang.common.util.StringUtil;
import com.juxi.lingshibang.common.util.login.CurrentUserUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.juxi.lingshibang.common.base.BaseController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户账号表 前端控制器
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@Slf4j
@RestController
@RequestMapping("/admin/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ISysPermissionService permissionService;

    @Autowired
    private CurrentUserUtil currentUserUtil;

    @Value("${jwt.encode.secret}")
    private String secret;

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    @OpLogAnnotation(source = DeviceEnum.PC , module =  ModuleEnum.LOGIN  ,desc = "用户登录[{loginDTO.phone}]")
    public Result<String> login(@RequestBody LoginDTO loginDTO){
        log.info("loginDTO:{}",loginDTO.toString());
        return userService.login(loginDTO);
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    @OpLogAnnotation(source =DeviceEnum.PC , module =  ModuleEnum.LOGINOUT  ,desc = "用户退出登录")
    public Result logout(HttpServletRequest request){
        //删除当前用户redis缓存
        String loginToken=currentUserUtil.getLoginToken(request);
        String userId= JwtUtil.getUserIdFromToken(loginToken,secret);
        if(StringUtil.isNotBlank(userId)){
            List<UserVO> userVOList = userService.logout(userId,loginToken);
            return ResponseUtil.SUCCESS("退出登录成功",userVOList);
        }else{
            return  ResponseUtil.Failure("用户退出失败");
        }
    }

    @ApiOperation(value = "用户新增或者修改")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody UserVO userVO) throws Exception {
        return userService.saveOrUpdate(userVO);
    }

    @ApiOperation(value = "当前用户拥有的权限")
    @GetMapping("/currentPermissions")
    public Result<JSONArray> currentPermissions(HttpServletRequest request){
        String loginToken=currentUserUtil.getLoginToken(request);
        if(StringUtil.isEmpty(loginToken)){
            return  ResponseUtil.Failure("Token为空");
        }
        String userId=JwtUtil.getUserIdFromToken(loginToken,secret);
        List<String> roles=userService.getUserRolesByUserId(userId);
        JSONArray permissionArray = permissionService.getPermissionsByUserId(userId, DeviceEnum.PC.getCode());
        //String[] menuIds=permissionService.getMenuIdsByUserId(userId, DeviceEnum.PC.getCode());
        LoginUserDTO userDTO=currentUserUtil.getUserInfo();
        Map<String,Object> resultMap=new HashMap<String,Object>();
        resultMap.put("user",userDTO);
        //企业管理员
        if(userDTO!=null&&
                UserTypeEnum.ADMIN.getCode()==userDTO.getType()){
            String[] adminPermission={"*"};
            resultMap.put("rules",adminPermission);
            resultMap.put("roles",adminPermission);
        }else {
            resultMap.put("rules",permissionArray);
            resultMap.put("roles", ListUtil.toArray(roles));
        }
        return ResponseUtil.SUCCESS("当前用户的权限",resultMap);
    }

    @ApiOperation(value = "用户列表查询")
    @GetMapping("/listByCondition")
    public Result listByCondition(@RequestParam Map<String, String> params){
        List<User> userList=userService.listByCondition(params);
        return ResponseUtil.SUCCESS("查询成功",userList);
    }


    @ApiOperation(value = "批量删除用户信息")
    @PostMapping("/delLogicUsers")
    public Result delLogics(@RequestBody List<String> Ids) throws Exception {
        return userService.delLogicUsers(Ids);
    }

    @ApiOperation(value = "批量更新用户信息")
    @PostMapping("/updateBatchUser")
    public Result updateBatch(@RequestBody List<User> userList) throws Exception {
        return userService.updateBatch(userList);
    }

    @ApiOperation(value = "密码重置")
    @PostMapping("/passwordReset")
    @OpLogAnnotation(source =DeviceEnum.PC , module =  ModuleEnum.PASSWORDRESET ,desc = "密码重置[ids={ids}]")
    public  Result  passwordReset(@RequestBody String[] ids){
        if(ids==null||ids.length==0){
            return ResponseUtil.Failure("请选择用户");
        }
        Boolean result= userService.passwordReset(ids);
        if(result){
            return   ResponseUtil.SUCCESS("密码重置成功");
        }
        return ResponseUtil.Failure("密码重置失败");
    }

    @ApiOperation(value = "当前用户密码修改")
    @PostMapping("/curUserPassWordModify")
    public Result curUserPassWordModify(@RequestBody String passWord){
        return  userService.curUserPassWordModify(passWord);
    }

    @ApiOperation(value = "查询用户信息")
    @GetMapping("/getUserInfo")
    public Result<LoginUserDTO> getUserInfo(@RequestParam String userId){
        LoginUserDTO loginUserDTO=userService.getUserInfo(userId);
        return ResponseUtil.SUCCESS(CommonCodeEnum.SUCCESS,loginUserDTO);
    }
}
