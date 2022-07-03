package com.course.rabbitmqproducer.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kestraa.apiproducer.dto.EventDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class EventDtoProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendMessage(EventDTO eventDto) throws JsonProcessingException {

		rabbitTemplate.convertAndSend("cyborg.exchange", "cyborg.routingkey", eventDto);

	}
	
}
