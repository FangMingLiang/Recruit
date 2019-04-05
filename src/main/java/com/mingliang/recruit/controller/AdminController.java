package com.mingliang.recruit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.ws.RequestWrapper;

@Controller
public class AdminController {
    @RequestMapping("/admin/index")
    public String adminIndex(){
        return "admin/index";
    }

}
