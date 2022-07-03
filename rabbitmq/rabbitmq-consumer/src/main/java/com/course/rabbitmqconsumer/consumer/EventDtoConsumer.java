package com.course.rabbitmqconsumer.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.course.rabbitmqconsumer.entity.EventDTO;

@Component
@RabbitListener(queues = {"cyborg.queue"})
public class EventDtoConsumer {
	
	@RabbitHandler
	private void listen(EventDTO eventDTO){
		
		System.out.println(eventDTO);
	}

}
