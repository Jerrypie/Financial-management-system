package com.example.demo.controll;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginControll {
    
	@RequestMapping( {"","/","/index"} )
	public String loginUp() {
	    return "index.html";
    }
	
	@PostMapping("/login")
	public String hello() {
	    return "/hello";
	}
}