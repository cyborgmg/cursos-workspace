package com.course.rabbitmqconsumer.consumer;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class FixedRateConsumer {
	
	@RabbitListener(queues="course.fixedrate", concurrency="3")
	public void listen(String message) throws InterruptedException {
		Thread.sleep(ThreadLocalRandom.current().nextInt(2000));
		System.out.println(Thread.currentThread().getName()+" Consuming : "+ message);
	}

}
