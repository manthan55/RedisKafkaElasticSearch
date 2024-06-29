package com.manthan.rediskafkaelasticsearch.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloWorldController {

    @GetMapping("/")
    public String HelloWorld(){
        return "Hello World!!!";
    }
}
