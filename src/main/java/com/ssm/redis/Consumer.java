/*package com.ssm.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class Consumer extends JedisPubSub {

	public void onPMessage(String pattern, String channel, String message) {
		System.out.println("接收到一条推流消息，准备推流," + pattern + "=" + channel + "="
				+ message);
	}
 
	public void onMessage(String channel, String message) {
		System.out.println("onMessage()," + channel + "=" + message);
	}
 
	public static void main(String[] args) {
		Consumer c = new Consumer();
		Jedis jedis = JedisPoolUtil.getJedis();
		jedis.subscribe(c, "channel1");//handle by onMessage()
		//jedis.psubscribe(c, new String[]{"chan*"}); // handle by onPMessage()
	}

}
*/