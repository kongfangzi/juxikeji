package com.juxi.lingshibang.common.redis;

import com.juxi.lingshibang.common.enums.RedisLimitEnum;
import com.juxi.lingshibang.common.util.LocalDateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 * @author xiaoxiong
 */
public class RedisUtil {

    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    /**
     * 存储string数据
     * @param redisTemplate
     * @param key
     * @param t
     * @return
     */
    public static boolean storeObject(StringRedisTemplate redisTemplate, String key, String t) {
        boolean flag = false;
        if (key == null || t == null) {
            throw new RuntimeException("key or t is null");
        } else {
            try {
                redisTemplate.opsForValue().set(key, t);
                flag = true;
            } catch (Exception e) {
                logger.error("error info ", e);
            }

        }
        return flag;
    }

    /**
     * 存储string数据
     * @param redisTemplate
     * @param key
     * @param t
     * @param timeout
     * @param unit
     * @return
     */
    public static boolean storeObject(StringRedisTemplate redisTemplate, String key, String t, long timeout, TimeUnit unit) {
        boolean flag = false;
        if (key == null || t == null) {
            throw new RuntimeException("key or t is null");
        } else {
            try {
                redisTemplate.opsForValue().set(key, t, timeout, unit);
                flag = true;
            } catch (Exception e) {
                logger.error("error info ", e);
            }

        }
        return flag;
    }

    /**
     * 获取redis中的数据信息
     * @param redisTemplate
     * @param key
     * @return
     */
    public static String getObject(StringRedisTemplate redisTemplate, String key) {
        String str = "";
        if (key == null) {
            throw new RuntimeException("key is null");
        } else {
            str = redisTemplate.opsForValue().get(key);
        }
        return StringUtils.hasText(str) ? str : "";
    }

    /**
     * 存储set数据
     * @param redisTemplate
     * @param key
     * @param set
     * @return
     */
    public static boolean storeSetValue(StringRedisTemplate redisTemplate, String key, Set<String> set) {
        boolean flag = false;
        if (key == null || set == null) {
            throw new RuntimeException("key or list is null");
        } else {
            try {
                String[] t = set.toArray(new String[set.size()]);
                redisTemplate.opsForSet().add(key, t);
                flag = true;
            } catch (Exception e) {
                logger.error("redis error info : ", e);
            }
        }
        return flag;
    }

    /**
     * 存储set数据
     * @param redisTemplate
     * @param key
     * @param set
     * @param expires
     * @param timeUnit
     * @return
     */
    public static boolean storeSetValue(StringRedisTemplate redisTemplate, String key, Set<String> set, int expires, TimeUnit timeUnit) {
        boolean flag = false;
        if (key == null || set == null) {
            throw new RuntimeException("key or list is null");
        } else {
            try {
                if (set.size() > 0) {
                    String[] t = set.toArray(new String[set.size()]);
                    redisTemplate.opsForSet().add(key, t);
                    redisTemplate.expire(key, expires, timeUnit);
                }
                flag = true;
            } catch (Exception e) {
                logger.error("redis error info : ", e);
            }
        }
        return flag;
    }

    /**
     * 获取set数据
     * @param redisTemplate
     * @param key
     * @return
     */
    public static Set<String> getSetValue(StringRedisTemplate redisTemplate, String key) {
        if (key == null) {
            throw new RuntimeException("redis key is null");
        } else {
            return redisTemplate.opsForSet().members(key);
        }
    }

    /**
     * 删除key对应的集合所包含的set
     * @param redisTemplate
     * @param key
     * @param set
     * @return
     */
    public static boolean removeSetValue(StringRedisTemplate redisTemplate, String key, Set<String> set) {
        boolean flag = false;
        if (key == null || set == null) {
            throw new RuntimeException("key or list is null");
        } else {
            try {
                String[] t = set.toArray(new String[set.size()]);
                redisTemplate.opsForSet().remove(key, t);
                flag = true;
            } catch (Exception e) {
                logger.error("redis error info : ", e);
            }
        }
        return flag;
    }

    /**
     * 删除key
     * @param redisTemplate
     * @param key
     */
    public static boolean deleteKey(StringRedisTemplate redisTemplate, String key) {
        if (key != null) {
            return redisTemplate.delete(key);
        }
           return  false;
    }

    /**
     * 删除keys
     * @param redisTemplate
     * @param keys
     */
    public static void deleteKey(StringRedisTemplate redisTemplate, List<String> keys) {
        if (keys != null && keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 发送订阅消息
     */
    public static void sendPublishMsg(StringRedisTemplate redisTemplate, String redisCmd, Object object) {
        logger.debug("向redis通道[" + redisCmd + "]发送消息：" + object);
        redisTemplate.convertAndSend(redisCmd, object);
    }

    /**
     * 获取key的过期时间
     * @param redisTemplate
     * @param key
     * @return 过期返回-2
     */
    public static Long getExpire(StringRedisTemplate redisTemplate, String key) {
        if (key != null) {
            return redisTemplate.getExpire(key);
        }
        return 0L;
    }

    /**
     * key是否有值
     * @param redisTemplate
     * @param key
     * @return
     */
    public static boolean hasKey(StringRedisTemplate redisTemplate, String key) {
        if (key == null) {
            throw new RuntimeException("key is null");
        }
        return redisTemplate.hasKey(key);
    }

    /**
     * 增加一个自动递增的key
     * @param redisTemplate
     * @param key
     * @return
     */
    public static Integer incr(StringRedisTemplate redisTemplate, String key, long time, TimeUnit timeUnit) {
        RedisAtomicInteger entityIdCounter = new RedisAtomicInteger(key, redisTemplate.getConnectionFactory());
        Integer increment = entityIdCounter.getAndIncrement();
        /** //初始设置过期时间 **/
        boolean isExists = (increment.intValue() == 0 || increment == null) && time > 0;
        if (isExists) {
            entityIdCounter.expire(time, timeUnit);
        }
        return increment == null ? 0 : increment;
    }

    /**
     * 设置一个过期的的值(高版本已经支持一步搞定)
     * @param stringRedisTemplate
     * @param redisKey
     * @param expireTime
     * @return
     */
    public static boolean setIfAbsent(StringRedisTemplate stringRedisTemplate, String redisKey, long expireTime) {
        boolean success = stringRedisTemplate.opsForValue().setIfAbsent(redisKey, "0");
        stringRedisTemplate.expire(redisKey, expireTime, TimeUnit.SECONDS);
        return success;
    }

    public static boolean isExistValue(StringRedisTemplate redisTemplate, String redisKey) {
        String str = redisTemplate.opsForValue().get(redisKey);
        return StringUtils.hasText(str);
    }

    /**
     * redis自增  用作次数限制，比如说10分钟限制访问3次
     * @param key
     * @param stringRedisTemplate
     * @param redisLimitEnum      {@link RedisLimitEnum}
     * @return
     */
    public static Long incrIfAbsent(StringRedisTemplate stringRedisTemplate, String key,
                                    RedisLimitEnum redisLimitEnum) {
        String sCount = stringRedisTemplate.opsForValue().get(key);
        Long count = sCount == null ? 0L : Long.valueOf(sCount);
        long expireTime = LocalDateUtil.getSecondLeftCurrentDay();
        if (count == 0) {
            ++count;
            stringRedisTemplate.opsForValue().set(key, count.toString(), expireTime,
                    redisLimitEnum.getTimeUnit());
        } else {
            count = stringRedisTemplate.boundValueOps(key).increment(1);
            //触发临界值，重新设置过期时间
            if (count.longValue() == redisLimitEnum.getTime().longValue()) {
                stringRedisTemplate.boundValueOps(key).expire(expireTime, redisLimitEnum.getTimeUnit());
            }
        }
        return count;
    }

    public static Set<String> hashScan(StringRedisTemplate redisTemplate, String keyPattern, long count) {
        Set<String> keysTmp = new HashSet<>();
        ScanOptions scanOptions = ScanOptions.scanOptions()
                .match(keyPattern + "*")
                .count(count)
                .build();
        try (Cursor<byte[]> cursor = redisTemplate.execute((RedisCallback<Cursor<byte[]>>) connection
                -> connection.scan(scanOptions))) {
            while (cursor.hasNext()) {
                keysTmp.add(new String(cursor.next()));
            }
        } catch (IOException e) {
            logger.error("redis scan key 出现异常", e);
        }
        return keysTmp;
    }


    /**
     * 向zset中添加元素
     * @param redisTemplate
     * @param key
     * @param value
     * @param score
     */
    public static void zAdd(RedisTemplate redisTemplate, String key, String value, double score) {
        redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 删除zset中指定的元素
     * @param redisTemplate
     * @param key
     * @param value
     */
    public static void zRemove(RedisTemplate redisTemplate, String key, String... value) {
        redisTemplate.opsForZSet().remove(key, value);
    }

    /**
     * 为指定元素加分
     * @param redisTemplate
     * @param key
     * @param value
     * @param score
     * @return 返回加分后的得分
     */
    public static Double incrementScore(RedisTemplate redisTemplate, String key, String value, double score) {
        Double s = redisTemplate.opsForZSet().incrementScore(key, value, score);
        return s;
    }


    /**
     * 获得指定元素的分数
     * @param redisTemplate
     * @param key
     * @param value
     * @return
     */
    public static Double score(RedisTemplate redisTemplate, String key, String value) {
        Double s = redisTemplate.opsForZSet().score(key, value);
        return s;
    }


    /**
     * 返回集合内元素的排名，以及分数（从大到小）
     * @param redisTemplate
     * @param start         起始 0 到- 1 为全部
     * @param end           结束
     * @return
     */
    public static List<String> range(RedisTemplate redisTemplate, String key, int start, int end) {
        Set<ZSetOperations.TypedTuple<String>> tuples =
                redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
        List<String> setValues = new ArrayList<>();
        for (ZSetOperations.TypedTuple<String> tuple : tuples) {
            setValues.add(tuple.getValue());
        }
        return setValues;
    }

    //===============================list=================================

    /**
     * 获取list缓存的内容
     * @param key   键
     * @param start 开始
     * @param end   结束  0 到 -1代表所有值
     * @return
     */
    public static List lGet(RedisTemplate redisTemplate, String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     * @param key 键
     * @return
     */
    public static long lGetListSize(RedisTemplate redisTemplate, String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     * @param key   键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public static Object lGetIndex(RedisTemplate redisTemplate, String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将list放入缓存
     * @param key   键
     * @param value 值
     * @return
     */
    public static boolean lSet(RedisTemplate redisTemplate, String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public static boolean lSet(RedisTemplate redisTemplate, String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key   键
     * @param value 值
     * @return
     */
    public static boolean listSet(RedisTemplate redisTemplate, String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public static boolean listSet(RedisTemplate redisTemplate, String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */
    public static boolean lUpdateIndex(RedisTemplate redisTemplate, String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除N个值为value
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public static long lRemove(RedisTemplate redisTemplate, String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 移除队列中最先插入的一个值，并返回删除的值
     * @param key 键
     * @return 删除的值
     */
    public static Object lRemoveLast(RedisTemplate redisTemplate, String key) {
        try {
            Object removeList = redisTemplate.opsForList().leftPop(key);
            return removeList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 保留区间内的元素，区间外的全部删除
     * @param key   键
     * @param start 区间开始
     * @param end   区间结束
     */
    public static void lTrim(RedisTemplate redisTemplate, String key, int start, int end) {
        try {
            redisTemplate.opsForList().trim(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * list中是否存在key
     * @param key 键
     * @return 删除的值
     */
    public static boolean lHasKey(RedisTemplate redisTemplate, String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    //================================Map=================================

    /**
     * HashGet
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public static Object hget(RedisTemplate redisTemplate, String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public static Map<Object, Object> hmget(RedisTemplate redisTemplate, String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public static boolean hmset(RedisTemplate redisTemplate, String key, Map<Object, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public static boolean hmset(RedisTemplate redisTemplate, String key, Map<Object, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public static boolean hset(RedisTemplate redisTemplate, String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public static boolean hset(RedisTemplate redisTemplate, String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public static void hdel(RedisTemplate redisTemplate, String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public static boolean hHasKey(RedisTemplate redisTemplate, String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     */
    public static double hincr(RedisTemplate redisTemplate, String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return
     */
    public static double hdecr(RedisTemplate redisTemplate, String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }


}
