/*package com.ssm.redis;

import redis.clients.jedis.Jedis;

public class Publisher {
	
	*//**   
	 * @Title: publish   
	 * @Description: TODO 退入消息到消息通道
	 * @param: @param channel
	 * @param: @param message      
	 * @return: void      
	 * @throws   
	 *//*  
	public static  void publish(String channel,String message){
		Jedis jedis = null;
		try {
			jedis = JedisPoolUtil.getJedis();
			jedis.publish(channel, message);
		} finally {
			// TODO: handle finally clause
			jedis.close();
		}
	}
	
	*//**   
	 * @Title: publish   
	 * @Description: TODO(推入消息到消息通道)   
	 * @param: @param channel
	 * @param: @param message      
	 * @return: void      
	 * @throws   
	 *//*  
	public static  void publish(byte[] channel,byte[] message){
		Jedis jedis = null;
		try {
			jedis = JedisPoolUtil.getJedis();
			jedis.publish(channel, message);
		} finally {
			// TODO: handle finally clause
			jedis.close();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jedis jedis = null;
		
		try {
			jedis = JedisPoolUtil.getJedis();
			jedis.publish("channel1", "message from channel1");
			jedis.publish("channel1", "message2 from channel1");
			jedis.publish("channel2", "message from channel2");
		} finally {
			// TODO: handle finally clause
			jedis.close();
		}
	}

}
*/