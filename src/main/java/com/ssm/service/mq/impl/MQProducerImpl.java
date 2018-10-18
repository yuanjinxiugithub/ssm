package com.ssm.service.mq.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssm.domain.mq.RabbitMessage;
import com.ssm.service.mq.MQProducer;

@Component
public class MQProducerImpl implements MQProducer{
	private static Logger log = LoggerFactory.getLogger(MQProducerImpl.class);
	@Autowired
	private AmqpTemplate amqpTemplate;

	@Override
	public void sendDataToQueue(RabbitMessage message) {
		System.out.println(message);
		try {
			// TODO Auto-generated method stub
			amqpTemplate.convertAndSend(message.getExchange(), message.getRouteKey(), message);//
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage(),e);
		}
	}
	
	

}
