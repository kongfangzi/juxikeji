package com.juxi.lingshibang.common.util.login;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.juxi.lingshibang.common.base.Constants;
import com.juxi.lingshibang.common.base.Result;
import com.juxi.lingshibang.common.enums.ErrorCodeEnum;
import com.juxi.lingshibang.common.enums.JwtEnum;
import com.juxi.lingshibang.common.exception.BizException;
import com.juxi.lingshibang.common.model.dto.LoginUserDTO;
import com.juxi.lingshibang.common.redis.RedisUtil;
import com.juxi.lingshibang.common.util.JwtUtil;
import com.juxi.lingshibang.common.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 当前登录用户工具
 *
 * @author yks
 * @date 2019-11-22
 */
@Slf4j
@Component
public class CurrentUserUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired(required=true)
    private RestTemplate restTemplate;

    @Value("${jwt.encode.secret}")
    private String secret;


    /**
     * 获取当前用户登录来源
     *
     * @return
     */
    public String getUserLoginSource() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return RequestUtil.getLoginRequestHeaderInfo(request, "loginSource");
    }


    /**
     * 获取当前用户token
     *
     * @return
     */
    public String getUserLoginToken() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return getLoginToken(request);
    }

    /**
     * 判断当前用户是否已登录
     *
     * @return userId
     */
    public boolean isUserLogin() {
        try {
            HttpServletRequest request =
                    ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String loginToken = getLoginToken(request);
            return StringUtils.hasText(loginToken) && !JwtUtil.isTokenExpired(loginToken, secret);
        } catch (Exception e) {
            log.warn("登录已过期,e={}", e);
            return false;
        }
    }

    /**
     * 获取登录用户信息(抛出异常)
     *
     * @return UserDTO
     */
    public LoginUserDTO getUserInfo() throws BizException {
        if (RequestContextHolder.getRequestAttributes() == null) {
            throw new BizException(ErrorCodeEnum.ERROR_400);
        }
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String loginToken = getLoginToken(request);
        if (!StringUtils.hasText(loginToken)) {
            log.error("loginToken:{}", loginToken);
            //return null;
            throw new BizException(ErrorCodeEnum.ACCESS_TOKEN_INVALID);
        }
        String userId = JwtUtil.getUserIdFromToken(loginToken, secret);
        final String redisJWTKey = LoginUtil.getRedisCacheLoginJWTKey(userId);
        String cacheJwt = RedisUtil.getObject(stringRedisTemplate, redisJWTKey);
        if (!StringUtils.hasText(cacheJwt)) {
            log.error("缓存token 已过期");
            throw new BizException(ErrorCodeEnum.ACCESS_TOKEN_EXPIRE);
        }
        if (!loginToken.equals(cacheJwt)) {
            log.error("登录token 和缓存token 不一致，loginToken={},cacheJwt={}", loginToken, cacheJwt);
            throw new BizException(ErrorCodeEnum.ACCESS_TOKEN_INVALID);
        }
        final String redisKey = LoginUtil.getRedisCacheLoginUserKey(userId);
        String userJson = RedisUtil.getObject(stringRedisTemplate, redisKey);
        if (!StringUtils.hasText(userJson)) {
            log.error("userJson:{}", userJson);
            throw new BizException(ErrorCodeEnum.LOGOUT);
        }
        return JSONObject.parseObject(userJson, LoginUserDTO.class);
    }

    /**
     * 获取登录用户信息
     *
     * @return UserDTO
     */
    public LoginUserDTO getLoginUser() {
        if (RequestContextHolder.getRequestAttributes() == null) {
            throw new BizException(ErrorCodeEnum.ERROR_400);
        }
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String loginToken = getLoginToken(request);
        if (!StringUtils.hasText(loginToken)) {
            log.info("Token为空:{}", loginToken);
            throw new BizException(ErrorCodeEnum.ACCESS_TOKEN_EMPTY);
        }
        String userId = JwtUtil.getUserIdFromToken(loginToken, secret);
        String redisKey = LoginUtil.getRedisCacheLoginUserKey(userId);
        String userJson = RedisUtil.getObject(stringRedisTemplate, redisKey);
        if (!StringUtils.hasText(userJson)) {
            Result result = restTemplate.getForObject(
                    Constants.RpcRequestAddress.getUserInfo.concat("?userId=").concat(userId),
                    Result.class);
            Object userObject = result.getData();
            if (userObject != null) {
                return JSONObject.parseObject(JSON.toJSONString(userObject), LoginUserDTO.class);
            }
            log.info("用户为空");
            throw new BizException(ErrorCodeEnum.USER_NOT_EXIST);
        }
        return JSONObject.parseObject(userJson, LoginUserDTO.class);
    }

    /**
     * 获取登录token
     *
     * @param request
     * @return
     */
    public String getLoginToken(HttpServletRequest request) {
        return RequestUtil.getLoginRequestHeaderInfo(request, JwtEnum.WEB_FRONT.getTokenName());
    }

}
