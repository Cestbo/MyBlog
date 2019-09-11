package com.example.demo.controller;

import com.example.demo.pojo.CommentDTO;
import com.example.demo.pojo.QuestionDTO;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @RequestMapping("/question/{id}")
    public String question(@PathVariable(name = "id") int id, Model model)
    {
        QuestionDTO questionDTO=questionService.getQuesDTOById(id);
        List<CommentDTO> commentDTOS=questionService.getCommentsByParentId(id);
        //考虑并发，不能采取先查询后加一的策略，会导致阅读数不准确
        /*Integer view_count=questionDTO.getView_count();
        if (view_count==null)
            view_count=1;
        else
        {
            view_count++;
        }
        questionDTO.setView_count(view_count);

        questionService.insertOrUpdate(questionDTO,String.valueOf(questionDTO.getId()));*/
        //更新阅读数
        questionService.incView_count(id);
        model.addAttribute("questionDTO",questionDTO);
        model.addAttribute("commentDTOs",commentDTOS);
        return "question";
    }
}
