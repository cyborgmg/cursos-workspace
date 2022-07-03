package com.course.rabbitmqproducer;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.rabbitmqproducer.entity.Picture;
import com.course.rabbitmqproducer.producer.Picture2Producer;
import com.course.rabbitmqproducer.producer.PictureProducer;

@SpringBootApplication
public class RabbitmqProducerApplication implements CommandLineRunner {
	
	@Autowired
	private Picture2Producer picture2Producer;
	
	private List<String> TYPES = Arrays.asList("jpg","png","svg");
	private List<String> SOURCES = Arrays.asList("mobile","web");

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 10; i++) {
			Picture p = new Picture();
			p.setName("Picture"+i);
			p.setSource( SOURCES.get( i % SOURCES.size() ) );
			p.setType( TYPES.get( i % TYPES.size() ) );
			
			if(p.getSource().equals("mobile")){
				p.setSize(4010);
			}else{
				p.setSize(ThreadLocalRandom.current().nextLong(1, 1000));
			}	
			
			picture2Producer.sendMessage(p);
		}
	}

}

