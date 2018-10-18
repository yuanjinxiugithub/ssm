/*package com.ssm.listener;

import javax.annotation.Resource;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisTestMessageListener implements MessageListener {

	@SuppressWarnings("rawtypes")
	@Resource
    private RedisTemplate redisTemplate;
	
	
	@Override
	public void onMessage(Message message, byte[] arg1) {
		// TODO Auto-generated method stub
		String topic = (String)redisTemplate.getStringSerializer().deserialize(message.getChannel());
		Object body = redisTemplate.getValueSerializer().deserialize(message.getBody());  
		String msg = String.valueOf(body);
		System.out.println(topic);
		System.out.println("接受消息："+msg);

	}

}
*/