package com.example.financialmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

    //将 / /index map到默认index.html
    @RequestMapping({"", "/", "/index"})
    public String IndexPage() {
        return "index";
    }
    @RequestMapping({"/signup"})
    public String signupPage() {
        return "signup";
    }
}
