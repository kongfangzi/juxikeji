package com.juxi.lingshibang.common.enums;


/**
 * jwt 枚举
 * @author yks
 * @date 2019-11-19
 */
public enum JwtEnum {

    /**
     * 云平台
     */
    WEB_FRONT(1,
              30 * 24 * 60 * 60 * 1000L,
              "/api/",
              "mes-token"),
    ;

    /**
     * app 类型
     */
    private int appType;

    /**
     * token过期时间(毫秒)
     */
    private long expireTime;

    /**
     * 请求路径前缀
     */
    private String reqPrefix;

    /**
     * token key
     */
    private String tokenName;

    JwtEnum(int appType, long expireTime, String reqPrefix, String tokenName) {
        this.appType = appType;
        this.expireTime = expireTime;
        this.reqPrefix = reqPrefix;
        this.tokenName = tokenName;
    }

    /**
     * 根据请求路径获取tokenName
     * @param requestPath
     * @return
     */
    public static JwtEnum getByPath(String requestPath) throws Exception {
        for (JwtEnum jwtEnum : JwtEnum.values()) {
            if (requestPath.startsWith(jwtEnum.getReqPrefix())) {
                return jwtEnum;
            }
        }
        throw new Exception(ErrorCodeEnum.ACCESS_TOKEN_INVALID.getErrorMsg());
    }

    public static JwtEnum getByAppType(int appType) {
        for (JwtEnum e : JwtEnum.values()) {
            if (e.getAppType() == appType) {
                return e;
            }
        }
        throw new IllegalArgumentException("wrong template appType: " + appType);
    }

    public int getAppType() {
        return appType;
    }

    public void setAppType(int appType) {
        this.appType = appType;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public String getReqPrefix() {
        return reqPrefix;
    }

    public void setReqPrefix(String reqPrefix) {
        this.reqPrefix = reqPrefix;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    /**
     * 生成token的redis的key
     * @param userId
     * @return
     */
    public String generateTokenRedisKey(String userId) {
        return this.getTokenName() + ":" + this.getAppType() + ":" + userId;
    }


}
