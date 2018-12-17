package com.example.demo.controll;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldControll {
    
	@RequestMapping("/hello")
	public String hello() {
	    return "Hello World!";
	}
	
}