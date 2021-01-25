package com.juxi.lingshibang.common.util.login;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.juxi.lingshibang.common.base.Constants;
import com.juxi.lingshibang.common.constants.UserConstants;
import com.juxi.lingshibang.common.model.dto.LoginUserDTO;
import com.juxi.lingshibang.common.model.dto.UserPermissionDTO;
import com.juxi.lingshibang.common.redis.RedisUtil;
import lombok.experimental.UtilityClass;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * 登录工具类
 * @date 2020-02-27
 */
@UtilityClass
public class LoginUtil {

    /**
     * 登录过期时间
     */
    private static final long LOGIN_EXPIRE_TIME = 30 * 24 * 60 * 60L;


    /**
     * 生成登录redis 缓存 JWT 的 key
     * @param userId
     * @return
     */
    public String getRedisCacheLoginJWTKey(String userId) {
        return Constants.redisKey.APP_NAME
                        .concat(Constants.redisKey.REDIS_KEY_SPLIT)
                        .concat(Constants.redisKey.SUB_NAME_LOGIN_JWT)
                        .concat(Constants.redisKey.REDIS_KEY_SPLIT)
                        .concat(userId);
    }

    /**
     * 生成登录redis 缓存 用户对象 的 key
     * @param userId
     * @return
     */
    public String getRedisCacheLoginUserKey(String userId) {
        return Constants.redisKey.APP_NAME
                        .concat(Constants.redisKey.REDIS_KEY_SPLIT)
                        .concat(Constants.redisKey.SUB_NAME_LOGIN_USER)
                        .concat(Constants.redisKey.REDIS_KEY_SPLIT)
                        .concat(userId);
    }

    /**
     * 生成登录redis 缓存 用户权限 的 key
     * @param userId
     * @return
     */
    public String getRedisCacheUserPermissionKey(String userId) {
        return Constants.redisKey.APP_NAME
                .concat(Constants.redisKey.REDIS_KEY_SPLIT)
                .concat(Constants.redisKey.SUB_NAME_USER_PERMISSIONS)
                .concat(Constants.redisKey.REDIS_KEY_SPLIT)
                .concat(userId);
    }

    /**
     * 生成登录redis 缓存 企业表 的 key
     * @param companyId
     * @return
     */
    public String getRedisCacheSchemaTablesKey(String companyId) {
        return Constants.redisKey.APP_NAME
                .concat(Constants.redisKey.REDIS_KEY_SPLIT)
                .concat(Constants.redisKey.SUB_NAME_SCHEMA_TABLES)
                .concat(Constants.redisKey.REDIS_KEY_SPLIT)
                .concat(companyId);
    }

    /**
     * 生成登录redis 缓存 默认Schema 的 key
     * @return
     */
    public String getRedisCacheSchemaDefaultKey() {
        return Constants.redisKey.APP_NAME
                .concat(Constants.redisKey.REDIS_KEY_SPLIT)
                .concat(UserConstants.DEFAUT_SCHEMA_NAME);
    }



    /**
     * 缓存登录token
     * @param stringRedisTemplate
     * @param loginToken
     * @param userId
     */
    public boolean cacheLoginToken(StringRedisTemplate stringRedisTemplate,
                                   String loginToken,
                                   String userId) {
        return RedisUtil.storeObject(stringRedisTemplate,
                              getRedisCacheLoginJWTKey(userId),
                              loginToken,
                              LOGIN_EXPIRE_TIME,
                              TimeUnit.SECONDS);
    }

    /**
     * 缓存登录用户相关信息
     * @param stringRedisTemplate
     * @param userDTO
     * @param userId
     */
    public boolean cacheUserInfo(StringRedisTemplate stringRedisTemplate,
                                 LoginUserDTO userDTO,
                                 String userId) {
        String cacheValue = JSONObject.toJSONString(userDTO);
        return RedisUtil.storeObject(stringRedisTemplate,
                              getRedisCacheLoginUserKey(userId),
                              cacheValue,
                              LOGIN_EXPIRE_TIME,
                              TimeUnit.SECONDS);
    }

    /**
     * 缓存登录用户权限相关信息
     * @param stringRedisTemplate
     * @param permissionDTOList
     * @param userId
     * @return
     */
    public boolean cacheUserPermissions(StringRedisTemplate stringRedisTemplate,
                                        List<UserPermissionDTO> permissionDTOList,
                                        String userId){
        String cacheValue =JSONArray.parseArray(JSONObject.toJSONString(permissionDTOList)).toJSONString();
        return RedisUtil.storeObject(stringRedisTemplate,
                getRedisCacheUserPermissionKey(userId),
                cacheValue,
                LOGIN_EXPIRE_TIME,
                TimeUnit.SECONDS);
    }

    /**
     * 缓存登录用户权限相关信息
     * @param stringRedisTemplate
     * @param tableList
     * @param companyId
     * @return
     */
    public boolean cacheSchemaTables(StringRedisTemplate stringRedisTemplate,
                                     List<String> tableList,
                                     String companyId){
        String cacheValue =JSONArray.parseArray(JSONObject.toJSONString(tableList)).toJSONString();
        return RedisUtil.storeObject(stringRedisTemplate,
                getRedisCacheSchemaTablesKey(companyId),
                cacheValue);
    }

    /**
     * 设置一个过期时间
     * @param stringRedisTemplate
     * @param userId
     * @return
     */
    public boolean  setLoginExpireTime(StringRedisTemplate stringRedisTemplate, String userId){
        return RedisUtil.setIfAbsent(stringRedisTemplate,
                         getRedisCacheLoginJWTKey(userId),LOGIN_EXPIRE_TIME)
                 &
                RedisUtil.setIfAbsent(stringRedisTemplate,
                          getRedisCacheLoginUserKey(userId),LOGIN_EXPIRE_TIME);
    }

    /**
     * 用户登出
     * @param stringRedisTemplate
     * @param userId
     */
    public boolean userLogout(StringRedisTemplate stringRedisTemplate, String userId) {
        Boolean dFlag1=RedisUtil.deleteKey(stringRedisTemplate,
                            getRedisCacheLoginUserKey(userId));
        Boolean dFlag2=RedisUtil.deleteKey(stringRedisTemplate,
                            getRedisCacheLoginJWTKey(userId));
        Boolean dFlag3=RedisUtil.deleteKey(stringRedisTemplate,
                           getRedisCacheUserPermissionKey(userId));
        return  dFlag1&dFlag2&dFlag3;
    }


    /**
     * 用户登录来源 缓存key
     * @param userId
     * @return
     */
    public String getUserLoginSourceKey(String userId) {
        return Constants.redisKey.APP_NAME.concat(Constants.redisKey.REDIS_KEY_SPLIT)
                .concat(Constants.redisKey.SUB_NAME_LOGIN)
                .concat(Constants.redisKey.REDIS_KEY_SPLIT)
                .concat(Constants.redisKey.USER_LOGIN_SOURCE)
                .concat(Constants.redisKey.REDIS_KEY_SPLIT)
                .concat(userId);
    }

    /**
     * 缓存用户登录来源
     * @param stringRedisTemplate
     * @param userId            用户id
     * @param loginSource       登录来源 1：PC登录 2：移动端登录
     * @return
     */
    public boolean setMemberLoginSource(StringRedisTemplate stringRedisTemplate,
                                        String userId,
                                        Integer loginSource) {
        return RedisUtil.storeObject(stringRedisTemplate,
                              getUserLoginSourceKey(userId),
                              loginSource.toString());
    }

    /**
     * 获取用户登录来源
     * @param stringRedisTemplate
     * @param userId
     * @return
     */
    public String getUserLoginSource(StringRedisTemplate stringRedisTemplate,
                                     String userId) {
        return RedisUtil.getObject(stringRedisTemplate,
                getUserLoginSourceKey(userId));
    }

}
