package com.course.rabbitmqproducer;

import com.kestraa.apiproducer.dto.EventDTO;
import com.course.rabbitmqproducer.producer.EventDtoProducer;
import com.crawler.mantra.dto.MercanteDto;
import com.crawler.mantra.model.Action;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class RabbitmqProducerApplication implements CommandLineRunner {
	
	@Autowired
	private EventDtoProducer eventDtoProducer;

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
			EventDTO event = new EventDTO();

			event.setEventDate(new Date());
			event.setUserId(1L);
			event.setCorporationId(237L);
			event.setProducer("local");
			event.setRoutingKey("crawler.mercante");
			event.setEntityId(1L);
			event.setEntityType("");
			event.setEntityVersion("");
			event.setAction(Action.CREATE);

			MercanteDto mercanteDto = new MercanteDto();
			mercanteDto.setCorporationId(237L);
			mercanteDto.setNumeroCeMercante("152005201637336");

			ObjectMapper mapper = new ObjectMapper();

			String jsonString = mapper.writeValueAsString(mercanteDto);

			event.setPayload(jsonString);

			eventDtoProducer.sendMessage(event);
			
			
			System.exit(0);
	}

}

