package com.ssm.service.mq;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.ssm.domain.mq.RabbitMessage;

public class MQConsumer implements ChannelAwareMessageListener{

	/*public void onMessage(RabbitMessage arg0)  {
		// TODO Auto-generated method stub
		RabbitMessage message = (RabbitMessage) arg0;
		System.out.println(message.getParams().toString());
	}
*/
	@Override
	public void onMessage(Message message, Channel arg1) throws Exception {
		// TODO Auto-generated method stub
		String s = new String(message.getBody(), "UTF-8");
		RabbitMessage rabbitMessage = JSON.parseObject(s, RabbitMessage.class);
		try {
		   arg1.basicAck(message.getMessageProperties().getDeliveryTag(), false);  //手动ack确认消息
			System.out.println(rabbitMessage.getParams().toString());
			Map<String,String> map = (Map<String,String>) rabbitMessage.getParams();
			Iterator<Entry<String, String>>  itera = map.entrySet().iterator();
			while(itera.hasNext()){
				Entry<String, String> strSet = itera.next();
				System.out.println("key:"+strSet.getKey()+";value:"+strSet.getValue());
			}
		} catch (Exception e) {
			// TODO: handle exception
			arg1.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);//重新放入队列
		   // arg1.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);//抛弃此条消息
		   // throw new Exception(e);
		}
	}

}
