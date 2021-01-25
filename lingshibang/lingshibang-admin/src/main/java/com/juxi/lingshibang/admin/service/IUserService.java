package com.juxi.lingshibang.admin.service;

import com.juxi.lingshibang.admin.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.juxi.lingshibang.admin.utilobject.bo.UserBO;
import com.juxi.lingshibang.admin.utilobject.dto.LoginDTO;
import com.juxi.lingshibang.admin.utilobject.vo.UserVO;
import com.juxi.lingshibang.common.base.Result;
import com.juxi.lingshibang.common.model.dto.LoginUserDTO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户账号表 服务类
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
public interface IUserService extends IService<User> {

    /**
     * 用户登录
     * @param loginDTO
     * @return
     */
    Result login(LoginDTO loginDTO);

    /**
     * 用户登录清除缓存
     * @param userId
     */
    List<UserVO> logout(String userId,String loginToken);

    /**
     * 刷新登录缓存
     * @param loginToken 登录token
     * @param userId     用户id
     */
    void refreshLoginCache(String loginToken, String userId);

    /**
     * 用户登录清除缓存
     * @param
     */
//    List<CompanyUserVO> logout(String userId, String loginToken);

    @Transactional
    Result saveOrUpdate(@Valid @RequestBody UserVO userVO) throws Exception;

    @Transactional
    Result saveBatch(List<UserBO> userList);

    @Transactional
    Result updateBatch(List<User> userList);

    /**
     * 根据用户ID查询用户角色
     * @param userId
     * @return
     */
    List<String> getUserRolesByUserId(String userId);

//    Result<User> getUserPageByDepParams(CustomerFormReq param);

    /**
     * 成员管理分页列表
     * @param
     * @return
     */
//    Result<User> getUserManageListByPage(CustomerFormReq param);

    Result delLogicUsers(List<String> ids);

    /**
     * 密码重置
     * @param ids
     * @return
     */
    Boolean passwordReset(String[] ids);

    /**
     * 当前用户密码修改
     * @param passWord
     * @return
     */
    Result curUserPassWordModify(String passWord);

    /**
     * 用户列表查询
     * @param params
     * @return
     */
    List<User> listByCondition(Map<String, String> params);



    LoginUserDTO getUserInfo(String userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(String userId);
}
