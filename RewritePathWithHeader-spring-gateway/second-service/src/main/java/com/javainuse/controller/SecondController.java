package com.javainuse.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumer")
public class SecondController {

    @GetMapping("/message/{id}/id")
    public String test(@PathVariable Integer id){
        return String.format("Hello %s JavaInUse Called in Second Service",id);
    }

}
