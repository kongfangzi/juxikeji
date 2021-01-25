package com.juxi.lingshibang.common.util;

import com.juxi.lingshibang.common.enums.JwtEnum;
import com.juxi.lingshibang.common.util.LocalDateUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 工具类
 * @author yks
 * @date 2020-02-26
 */
@Slf4j
@UtilityClass
public final class JwtUtil {

    private static final String CLAIM_KEY_USERID = "sub";

    /**
     * 签发JWT
     * @param userId 用户id
     * @param secret 加密串
     * @return
     */
    public String generateToken(String userId, final String secret, JwtEnum jwtEnum) {
        Map<String, Object> claims = new HashMap<>(16);
        claims.put(CLAIM_KEY_USERID, userId);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(Instant.now().toEpochMilli() + jwtEnum.getExpireTime()))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 获取token是否过期
     */
    public Boolean isTokenExpired(String token, final String secret) {
        try {
            if (!StringUtils.hasText(token)) {
                return false;
            }
            Date expiration = getExpirationDateFromToken(token, secret);
            return expiration.before(new Date());
        } catch (Exception e) {
            log.warn("登录token已过期=[{}],e=[{}]", token, e.getMessage());
            return true;
        }

    }

    /**
     * 根据token获取username
     */
    public String getUserIdFromToken(String token, final String secret) {
        //log.info("JWT-TOKEN:{}", token);
        String userId = getClaimsFromToken(token, secret).getSubject();
        return userId;
    }


    /**
     * 获取token的过期时间
     */
    public Date getExpirationDateFromToken(String token, final String secret) {
        Date expiration = getClaimsFromToken(token, secret).getExpiration();
        return expiration;
    }

    /**
     * 获取token的过期剩余秒
     * @param token
     * @param secret
     * @return
     */
    public Long getExpirationSecondLeft(String token, final String secret) {
        return LocalDateUtil.betweenSecond(LocalDateTime.now(),
                LocalDateUtil.date2LocalDateTime(JwtUtil.getExpirationDateFromToken(token, secret)));
    }


    /**
     * 解析JWT
     */
    private Claims getClaimsFromToken(String token, final String secret) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    /**
     * 刷新token
     * @param token  旧token
     * @param secret 加密串
     * @return
     */
    public String refreshToken(String token, final String secret) {
        String userId = getUserIdFromToken(token, secret);
        return generateToken(userId, secret, JwtEnum.WEB_FRONT);
    }

    public static void main(String[] args) {
        String token = JwtUtil.generateToken("123456", "E632A955E20251364C7193A4EA814E80", JwtEnum.WEB_FRONT);
        System.out.println("token = " + token);
        System.out.println("userId = " + getUserIdFromToken(token, "E632A955E20251364C7193A4EA814E80"));
        System.out.println(isTokenExpired("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOjMsImV4cCI6MTU4Mjk2NTEyN30.zw5AxHtmU97Bsy2rGTH12XjKXujgMbqwc5a3ThDX7SI5K7tfXcWjJO382_cwQO99hF9o0QUq2uB_nBkbDUHI6Q"
                , "E632A955E20251364C7193A4EA814E80"));
        System.out.println("新的token:" + refreshToken(token, "E632A955E20251364C7193A4EA814E80"));
    }


}
