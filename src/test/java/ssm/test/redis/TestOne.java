package ssm.test.redis;

import redis.clients.jedis.Jedis;

/**
 * @author yjx
 * redis 测试
 */
public class TestOne {
	
	public static void main(String[] args){
		  Jedis jedis = new Jedis("127.0.0.1",6379);
		  System.out.println("连接成功");
		   System.out.println("服务正在运行: "+jedis.ping());
		   jedis.set("runoobkey", "www.runoob.com");
	        // 获取存储的数据并输出
	      System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));
	}
}
