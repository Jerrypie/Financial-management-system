package com.example.financialmanagement.controller;

import com.example.financialmanagement.model.PageList;
import com.example.financialmanagement.service.MainService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class DividePage {

    private MainService mainService;

    @RequestMapping("/dividePage.do")
    public String dividePage(int currentPage, String username, Model model){
        PageList pageObj =  mainService.divdePage(currentPage,username,10);
        model.addAttribute("pageObj",pageObj);
        model.addAttribute("records",pageObj.getDataList());
        return "main";
    }
}
