package com.cpgps.canbus.common.mq.activemq;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import com.cpgps.canbus.common.mq.MqManager;

/**
 * @author wangshuai01@e6yun.com 2017年5月12日
 */
@Component
public class ActiveMqManager implements MqManager{
	@Autowired
	private JmsMessagingTemplate jmsTemplate;
	@Override
	public void send(String queueName, String msg) {
		Queue queue = new ActiveMQQueue(queueName);
		this.jmsTemplate.convertAndSend(queue, msg);
	}
  
	
}
