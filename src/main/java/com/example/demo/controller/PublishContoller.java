package com.example.demo.controller;

import com.example.demo.mapper.QuestionMapper;
import com.example.demo.pojo.Question;
import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishContoller {
    @Autowired
    private QuestionMapper questionMapper;

    @RequestMapping(value = "/publish",method = RequestMethod.GET)
    public String publish()
    {
        return "publish";
    }

    @RequestMapping(value = "/publish",method = RequestMethod.POST)
    public String dopublish(
            @Param("title")String title,
            @Param("description")String description,
            @Param("tag")String tag,
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
        questionMapper.insert(question);
        System.out.println("发布问题成功");
        return "redirect:/";

    }

}
