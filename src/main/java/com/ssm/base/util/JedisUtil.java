package com.ssm.base.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil {

	// Redis 服务器 IP
	private String address = "10.2.246.141";

	// Redis的端口号
	private int port = 6379;

	// 访问密码
	// private String password = "920619";

	// 连接 redis 等待时间
	private int timeOut = 10000;

	// 可用连接实例的最大数目，默认值为8；
	// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)
	private int maxTotal = 1024;

	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8
	private int maxIdle = 200;

	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
	private int maxWait = 10000;

	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的
	private boolean testOnBorrow = true;

	// 连接池
	private JedisPool jedisPool = null;

	//构造函数
	public JedisUtil() {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxIdle(maxIdle);
			config.setMaxTotal(maxTotal);
			config.setMaxWaitMillis(maxWait);
			config.setTestOnBorrow(testOnBorrow);
			jedisPool = new JedisPool(config,address,port,timeOut);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//获取应用实例
	public Jedis getJedis(){
		if(jedisPool !=null){
			return jedisPool.getResource();
		}
		return null;
	}
	
	//获取实例
	private static JedisUtil instance = null;	
	
	//获取单例 实例---使用静态工厂方法
	private static synchronized JedisUtil getInstance(){
		if(instance == null){
			instance = new JedisUtil();
		}
		return instance;
		
	}
	
			
			

}
