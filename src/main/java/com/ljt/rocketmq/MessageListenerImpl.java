package com.ljt.rocketmq;

import java.util.List;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;

public class MessageListenerImpl implements MessageListenerConcurrently {

	@Override
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
		for (MessageExt messageExt : msgs) {
			System.out.println(messageExt.toString());
			System.out.println( new String(messageExt.getBody()));
		}
		System.out.println("getDelayLevelWhenNextConsume="+context.getDelayLevelWhenNextConsume()+"getMessageQueue="+context.getMessageQueue().toString()+"getDelayLevelWhenNextConsume="+context.getDelayLevelWhenNextConsume());
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}
}
