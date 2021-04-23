package com.javasampleapproach.log4j2

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class SpringBootKotlinLog4J2Application

fun main(args: Array<String>) {
    SpringApplication.run(SpringBootKotlinLog4J2Application::class.java, *args)
}
