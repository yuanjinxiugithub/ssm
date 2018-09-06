package com.ssm.service.mq;

import com.ssm.domain.mq.RabbitMessage;

/**
 * @author yjx
 *  MQ 生产者
 */
public interface MQProducer {
	
   public void sendDataToQueue(RabbitMessage message);
}
