package com.juxi.lingshibang.api.utils;

import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisUtil {

	public JedisPool jedisPool;

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {

		this.jedisPool = jedisPool;
	}

	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String value = jedis.get(key);
		jedisPool.returnResource(jedis);
		return value;
	}

	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.set(key, value);
		jedisPool.returnResource(jedis);
		return result;
	}

	public String set(String key, int seconds, String value) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.setex(key, seconds, value);
		jedisPool.returnResource(jedis);
		return value;
	}

	public boolean exists(String key) {
		Jedis jedis = jedisPool.getResource();
		boolean result = jedis.exists(key);
		jedisPool.returnResource(jedis);
		return result;
	}

	public Long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.ttl(key);
		jedisPool.returnResource(jedis);
		return result;

	}

	public Long dell(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.del(key);
		jedisPool.returnResource(jedis);
		return result;

	}
}
