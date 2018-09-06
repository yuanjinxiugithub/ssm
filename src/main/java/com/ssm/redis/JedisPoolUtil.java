package com.ssm.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author yjx JedisPool 连接池 工具类
 */
public class JedisPoolUtil {

	public static JedisPoolConfig getJedisPoolConfig() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(0); // 最大等待连接中的设置，设置0为没有限制 最大空闲数
		config.setMaxWaitMillis(1000);// 最大
		return config;
	}

	public static JedisPool getJedisPool() {
		JedisPool jedisPool = new JedisPool(getJedisPoolConfig(), "localhost");
		return jedisPool;
	}

	public static Jedis getJedis() {
		Jedis jedis = getJedisPool().getResource();
		return jedis;
	}
}
