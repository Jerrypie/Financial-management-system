package com.example.financialmanagement.controll;

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
	public String logining(String UserId,String Password) {
		if ("xsc".equals(UserId) && "caiji".equals(Password)){
			return "/hello";
		}
		return "/loginError";  
	}
}