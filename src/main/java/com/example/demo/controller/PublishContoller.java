package com.example.demo.controller;

import com.example.demo.pojo.Question;
import com.example.demo.pojo.QuestionDTO;
import com.example.demo.pojo.User;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;

@Controller
public class PublishContoller {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/publish",method = RequestMethod.GET)
    public String publish(Model model)
    {
        model.addAttribute("nav_section","publish");
        return "publish";
    }

    @RequestMapping(value = "/publish",method = RequestMethod.POST)
    public String doPublish(
            @RequestParam(name = "title")String title,
            @RequestParam(name = "description")String description,
            @RequestParam(name = "tag")String tag,
            @RequestParam(name = "id") String id,
            HttpServletRequest request
    )
    {
        Question question=new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setGmt_create(System.currentTimeMillis());
        question.setGmt_modified(System.currentTimeMillis());
        //获取用户id
        User user= (User) request.getSession().getAttribute("user");
        question.setCreator(user.getId());
        questionService.insertOrUpdate(question,id);
        System.out.println("发布问题成功");
        return "redirect:/";

    }

    @RequestMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") int id , Model model)
    {
        QuestionDTO questionDTO=questionService.getQuesById(id);
        model.addAttribute("title",questionDTO.getTitle());
        model.addAttribute("description",questionDTO.getDescription());
        model.addAttribute("tag",questionDTO.getTag());
        model.addAttribute("id",id);
        return "publish";
    }

}
