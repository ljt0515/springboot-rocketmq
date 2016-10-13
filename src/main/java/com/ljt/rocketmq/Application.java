package com.ljt.rocketmq;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

@SpringBootApplication
@EnableScheduling
@ComponentScan
@EnableAutoConfiguration
@ServletComponentScan
public class Application {
	public static ApplicationContext applicationContext;

	public static void main(String[] args) throws Exception {
		SpringApplication app = new SpringApplication(Application.class);
		app.setWebEnvironment(false);
		Set<Object> set = new HashSet<Object>();
		set.add("classpath:applicationContext.xml");
		app.setSources(set);
		applicationContext = app.run(args);
		send();
	}

	private static void send() {
		try {
			DefaultMQProducer defaultMQProducer=(DefaultMQProducer) Application.applicationContext.getBean("rocketmqProduct");
			Message msg = new Message("PushTopic", "push", "rocketmq for test.".getBytes());
			SendResult result = defaultMQProducer.send(msg);
			System.out.println("id:" + result.getMsgId() + " result:" + result.getSendStatus());
		} catch (BeansException e) {
			e.printStackTrace();
		} catch (MQClientException e) {
			e.printStackTrace();
		} catch (RemotingException e) {
			e.printStackTrace();
		} catch (MQBrokerException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
