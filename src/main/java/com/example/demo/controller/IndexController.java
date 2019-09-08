package com.example.demo.controller;


import com.example.demo.pojo.Pagination;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class IndexController {


    @Autowired
    private QuestionService questionService;
    @RequestMapping("/")
    public String index( Model model,
                        @RequestParam(name = "page",defaultValue = "1") int page)
    {
        Pagination pagination=questionService.getPage(page);
        model.addAttribute("pagination",pagination);
        model.addAttribute("nav_section","home");
        return "index";
    }
}
