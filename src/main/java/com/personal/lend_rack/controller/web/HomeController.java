package com.personal.lend_rack.controller.web;

import org.springframework.stereotype.Controller;

@Controller
public class HomeController {
    public String home(){
        return "index";
    }
}
