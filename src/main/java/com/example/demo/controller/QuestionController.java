package com.example.demo.controller;

import com.example.demo.pojo.QuestionDTO;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @RequestMapping("/question/{id}")
    public String question(@PathVariable(name = "id") int id, Model model)
    {
        QuestionDTO questionDTO=questionService.getQuesById(id);
        model.addAttribute("questionDTO",questionDTO);
        return "question";
    }
}
