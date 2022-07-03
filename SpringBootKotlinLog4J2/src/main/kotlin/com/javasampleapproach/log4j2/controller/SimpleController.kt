package com.javasampleapproach.log4j2.controller

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class SimpleController {
	
    private val logger = LoggerFactory.getLogger(SimpleController::class.java)
	
    @RequestMapping("/")
    fun hello() : String{
        logger.debug("Debug message");
        logger.info("Info message");
        logger.warn("Warn message");
        logger.error("Error message");
        return "Done";
    }
}