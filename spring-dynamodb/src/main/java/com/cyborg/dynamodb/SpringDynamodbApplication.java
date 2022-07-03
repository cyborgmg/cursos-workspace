package com.cyborg.dynamodb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class SpringDynamodbApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringDynamodbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(UUID.randomUUID());
	}
}
