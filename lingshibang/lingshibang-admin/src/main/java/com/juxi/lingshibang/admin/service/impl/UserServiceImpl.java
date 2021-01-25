package com.juxi.lingshibang.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.google.common.base.Charsets;
import com.juxi.lingshibang.admin.constants.UserConstant;
import com.juxi.lingshibang.admin.entity.SysOperLog;
import com.juxi.lingshibang.admin.entity.User;
import com.juxi.lingshibang.admin.mapper.SysOperLogMapper;
import com.juxi.lingshibang.admin.mapper.SysPermissionMapper;
import com.juxi.lingshibang.admin.mapper.UserMapper;
import com.juxi.lingshibang.admin.service.ISysRoleUserService;
import com.juxi.lingshibang.admin.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juxi.lingshibang.admin.utilobject.bo.UserBO;
import com.juxi.lingshibang.admin.utilobject.dto.LoginDTO;
import com.juxi.lingshibang.admin.utilobject.vo.UserVO;
import com.juxi.lingshibang.common.base.Result;
import com.juxi.lingshibang.common.enums.*;
import com.juxi.lingshibang.common.model.dto.LoginUserDTO;
import com.juxi.lingshibang.common.model.dto.UserPermissionDTO;
import com.juxi.lingshibang.common.util.*;
import com.juxi.lingshibang.common.util.login.CurrentUserUtil;
import com.juxi.lingshibang.common.util.login.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户账号表 服务实现类
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private SysPermissionMapper permissionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    SysOperLogMapper sysOperLogMapper;

    @Autowired
    private ISysRoleUserService roleUserService;

    @Autowired(required=true)
    private CurrentUserUtil currentUserUtil;

    @Value("${jwt.encode.secret}")
    private String secret;

    /**
     * 用户登录
     * @param loginDTO
     * @return
     */
    @Override
    public Result login(LoginDTO loginDTO) {
        User user=null;
        List<User> userList=new ArrayList<>();
        String telPhone=loginDTO.getTelPhone();
        String userCode=loginDTO.getUserCode();
        String password=loginDTO.getPassword();
        //如果用户编码为空，对手机号码和登录密码验证
        if(StringUtil.isEmpty(userCode)){
            if(!ParameterChecker.checkPhone(telPhone)){
                return  ResponseUtil.Failure("请输入正确的手机号!");
            }
            if(StringUtil.isEmpty(password)){
                return ResponseUtil.Failure("密码为空!");
            }
            //根据用户登录名称查询用户
            QueryWrapper queryWrapper=new QueryWrapper<User>();
            queryWrapper.eq("tel_phone",telPhone);
            queryWrapper.eq("is_delete", ObjectStatusEnum.NORMAL.getCode());
            userList= userMapper.selectList(queryWrapper);
            if(CollectionUtils.isEmpty(userList)){
                return ResponseUtil.Failure("手机号不存在!");
            }
            if(userList.size()==1){
                user=userList.get(0);
            }
        }else{
            if(StringUtil.isEmpty(userCode)||
                    StringUtil.isEmpty(password)){
                return ResponseUtil.Failure("用户编号或者密码为空!");
            }
            //根据用户登录名称查询用户
            QueryWrapper queryWrapper=new QueryWrapper<User>();
            queryWrapper.eq("user_code",userCode);
            queryWrapper.eq("is_delete", ObjectStatusEnum.NORMAL.getCode());
            user=userMapper.selectOne(queryWrapper);
            if(user==null){
                return ResponseUtil.Failure("用户不存在!");
            }
        }
        //登录Token
        String loginToken =null;
        //用户Id
        String userId=null;
        if(user!=null){
            //密码校验
            String saltPassword = HashUtil.MD5(user.getSalt(),
                    Charsets.UTF_8, password);
            if(saltPassword.equals(user.getPassword())){
                log.info("用户{},登录验证成功!",user.getUserCode());
                loginToken = JwtUtil.generateToken(user.getId(), secret,
                        JwtEnum.WEB_FRONT);
                //用户redis缓存刷新
                refreshLoginCache(loginToken,user.getId());
                SysOperLog operLog = new SysOperLog();
                operLog.setTitle("user");
                operLog.setBusinessType(0);
                operLog.setMethod("login");
                operLog.setRequestMethod("POST");
                operLog.setOperatorType(0);
                operLog.setOperName(currentUserUtil.getUserInfo().getUsername());
//                operLog.
                sysOperLogMapper.insert(operLog);
                return  ResponseUtil.SUCCESS("登录成功",user);
            }
        }
        return  ResponseUtil.Failure("用户名或密码错误");
    }

    @Override
    public List<UserVO> logout(String userId, String loginToken) {
        UserVO userVO=new UserVO();
        //删除当前用户redis缓存
        LoginUtil.userLogout(stringRedisTemplate,userId);
        List<UserVO> userVOList=new ArrayList<>();
        userVO.setId(userId);
        userVO.setLoginToken(loginToken);
        userVOList.add(userVO);
        return userVOList;
    }

    /**
     * 用户注册/更新
     * @param userVO
     * @return
     * @throws Exception
     */
    @Override
    public Result saveOrUpdate(@Valid UserVO userVO) throws Exception {

        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        List<String> roleIds = new ArrayList<>();
        if(StringUtil.isNotBlank(user.getRoles())){
            List<String> roleIdsList = Arrays.asList(String.valueOf(user.getRoles().split(",")));
            roleIds = roleIdsList.stream().map(roleId -> roleId).collect(Collectors.toList());
        }

        if(StringUtil.isNotBlank(user.getUserCode())){
            LambdaQueryWrapper<User> query = new LambdaQueryWrapper();
            query.and(Wrapper -> Wrapper.eq(User::getUserCode, user.getUserCode())
                    .or().eq(User::getTelPhone, user.getTelPhone()));
            query.eq(User::getIsDelete, ObjectStatusEnum.NORMAL.getCode());
            //员工企业id、工号、手机号唯一性判断
            List<User> users = userMapper.selectList(query);
            User selfUser = userMapper.selectById(user.getId());
            users.remove(selfUser);
            if(users.size() > 0){
                return ResponseUtil.Failure("用户编号或手机号不可重复！");
            }
            //修改
            userMapper.updateById(user);
            //用户修改角色
            roleUserService.addOrUpdateRoleUser(user.getId(), roleIds);
            return ResponseUtil.SUCCESS("用户更新操作成功", user);
        }

        user.setUserCode(IdWorker.getIdStr());
        user.setSalt(DigestUtils.md5Hex(UUID.randomUUID().toString()
                + System.currentTimeMillis()
                + UUID.randomUUID().toString()));
        user.setPassword(HashUtil.MD5(user.getSalt(),
                Charsets.UTF_8, UserConstant.DEFAULT_PASSWORD));
        user.setStatus(UserStatusEnum.EFFECT.getCode());
        user.setType(UserTypeEnum.ORDINARY.getCode());
        user.setIsDelete(0);

        LambdaQueryWrapper<User> query = new LambdaQueryWrapper();
        query.and(Wrapper -> Wrapper.eq(User::getUserCode, user.getUserCode())
                .or().eq(User::getTelPhone, user.getTelPhone()));
        //过滤误删员工
        query.eq(User::getIsDelete, UserStatusEnum.EFFECT.getCode());
        //用户编号、手机号唯一性判断
        List<User> users = userMapper.selectList(query);
        if(users.size() > 0){
            return ResponseUtil.Failure("用户编号或手机号不可重复！");
        }
        userMapper.insert(user);
        //用户新增角色
        roleUserService.addOrUpdateRoleUser(user.getId(), roleIds);
        return null;
    }


    /**
     * 刷新登录缓存
     * @param loginToken 登录token
     * @param userId     用户id
     */
    @Override
    public void refreshLoginCache(String loginToken,String userId) {
        LoginUserDTO userDTO=userMapper.loginUserInfo(userId,
                ObjectStatusEnum.NORMAL.getCode());
        if (userDTO != null) {
            log.info("刷新登录缓存，userDTO ={}", userDTO.toString());
            //缓存用户token
            LoginUtil.cacheLoginToken(stringRedisTemplate, loginToken, userId);
            //缓存用户对象
            LoginUtil.cacheUserInfo(stringRedisTemplate, userDTO, userId);
            //缓存用户权限
            List<UserPermissionDTO> permissionDTOList=permissionMapper.listByUserId(userId, DeviceEnum.PC.getCode(),
                    null,ObjectStatusEnum.NORMAL.getCode());
            if(!CollectionUtils.isEmpty(permissionDTOList)){
                LoginUtil.cacheUserPermissions(stringRedisTemplate,permissionDTOList,userId);
            }
        }
    }

    @Override
    public Result saveBatch(List<UserBO> userList) {
        return null;
    }

    @Override
    public Result updateBatch(List<User> userList) {
        return null;
    }

    @Override
    public List<String> getUserRolesByUserId(String userId) {

        return userMapper.getUserRolesByUserId(userId,ObjectStatusEnum.NORMAL.getCode());
    }

    @Override
    public Result delLogicUsers(List<String> ids) {
        List<User> list = userMapper.selectBatchIds(ids);
        String selfId = currentUserUtil.getUserInfo().getId();
        for (User user: list) {
            if(selfId.equals(user.getId())){
                return ResponseUtil.Failure("用户不能删除自己！");
            }
            user.setIsDelete(ObjectStatusEnum.DELETE.getCode());
            userMapper.updateById(user);
        }
        return ResponseUtil.SUCCESS("批量逻辑删除用户成功",ids);
    }

    @Override
    public Boolean passwordReset(String[] ids) {
        LoginUserDTO loginUserDTO=currentUserUtil.getUserInfo();
        //查询用户设置的密码
        String defaultPassword = userMapper.selectById(ids).getPassword();
        if(StringUtil.isNotBlank(defaultPassword)){
            Integer result=userMapper.passwordReset(
                    defaultPassword,ids,loginUserDTO.getId());
            if(result>0){
                return true;
            }
        }
        return false;
    }

    /**
     * 修改密码
     * @param passWord
     * @return
     */
    @Override
    public Result curUserPassWordModify(String passWord) {
        //判断修改密码和原密码是否相同
        String userId=currentUserUtil.getUserInfo().getId();
        User user=userMapper.selectById(userId);
        String newPassWord=HashUtil.MD5(user.getSalt(),
                Charsets.UTF_8,passWord);
        if(newPassWord.equals(user.getPassword())){
            return ResponseUtil.Failure("新密码和原密码相同");
        }
        user.setPassword(newPassWord);
        userMapper.updateById(user);
        return ResponseUtil.SUCCESS("密码修改成功");
    }

    @Override
    public List<User> listByCondition(Map<String, String> params) {
        LoginUserDTO loginUserDTO=currentUserUtil.getUserInfo();
        params.put("company_id",loginUserDTO.getCompanyId());
        List<User> userList=userMapper.selectByCondition(params);
        return userList;
    }

    @Override
    public LoginUserDTO getUserInfo(String userId) {
        return userMapper.loginUserInfo(userId, ObjectStatusEnum.NORMAL.getCode());
    }

    @Override
    public List<Long> queryAllMenuId(String userId) {
        return baseMapper.queryAllMenuId(userId);
    }
}
