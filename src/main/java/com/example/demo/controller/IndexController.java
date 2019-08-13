package com.example.demo.controller;

import com.example.demo.service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {
    @Autowired
    NewService newService;
    @RequestMapping("/")
    public String index()
    {
        return "index";
    }
}
