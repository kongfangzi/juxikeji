package com.juxi.lingshibang.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;

/**
 * jedis分布式锁
 */

@Slf4j
public class JedisLock {

    private static String UNLOCK_LUA;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call('get', KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append("    return redis.call('del', KEYS[1]) ");
        sb.append("else ");
        sb.append("    return 0 ");
        sb.append("end");
        UNLOCK_LUA = sb.toString();
    }

    /**
     * 获取锁
     * @param redisTemplate
     * @param lockKey
     * @param requestId
     * @param expireTime    毫秒
     * @return
     */
    public static boolean lock(RedisTemplate redisTemplate,
                               String lockKey, String requestId, int expireTime) {
        try {
            RedisCallback redisCallback = connection -> {
                return connection.set(lockKey.getBytes(), requestId.getBytes(),
                        Expiration.milliseconds(expireTime), RedisStringCommands.SetOption.SET_IF_ABSENT);
            };
            Boolean bool = (Boolean) redisTemplate.execute(redisCallback);
            log.debug("获取redis分布式锁：" + lockKey + (bool ? "成功" : "失败"));
            return  bool;
        } catch (Exception e) {
            log.error("获取redis分布式锁异常：", e);
        }
        return false;
    }

    /**
     * 释放锁
     * @param redisTemplate
     * @param lockKey
     * @param requestId
     * @return
     */
    public static boolean unlock(RedisTemplate redisTemplate,
                                 String lockKey, String requestId) {
        try {
            Boolean execute = redisTemplate.delete(lockKey);
            log.debug("释放redis分布式锁：" + lockKey + (execute ? "成功" : "失败"));
            return execute;
        } catch (Exception e) {
            log.error("释放redis分布式锁异常：", e);
        }
        return false;
    }

}
