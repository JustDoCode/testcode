package com.cpgps.canbus.common.mq;

public interface MqManager {
	public void send(String queueName, String msg);
}
