package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PublishContoller {
    @RequestMapping("/publish")
    public String publish()
    {
        return "publish";
    }
}