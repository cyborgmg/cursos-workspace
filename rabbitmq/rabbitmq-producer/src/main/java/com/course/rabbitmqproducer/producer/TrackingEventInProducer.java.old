package com.course.rabbitmqproducer.producer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.rabbitmqproducer.entity.TrackingEventIn;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class TrackingEventInProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendMessage(List<TrackingEventIn> list) throws JsonProcessingException {
		
		Map<String, List<TrackingEventIn>> map = new HashMap<String, List<TrackingEventIn>>();
		
		map.put("create", list);
		
		rabbitTemplate.convertAndSend("kestraa.exchange", "documents.shipment", map);
		
	}
	
}
