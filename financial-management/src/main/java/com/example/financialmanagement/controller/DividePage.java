package com.example.financialmanagement.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class DividePage {

    @RequestMapping("/dividePage.do")
    public String divdePage(int currentPage,Model model){
        return "index";
    }
}
