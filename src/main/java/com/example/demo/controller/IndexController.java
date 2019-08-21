package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.Pagination;
import com.example.demo.pojo.User;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;
    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name = "page",defaultValue = "1") int page)
    {
        Cookie[] cookies = request.getCookies();
        if(cookies!=null && cookies.length>0)
        {
            for (Cookie cookie : cookies) {
                if ("user_token".equals(cookie.getName()))
                {
                    User user=userMapper.getUserByToken(cookie.getValue());
                    if(user!=null)
                        request.getSession().setAttribute("user",user);
                    break;
                }

            }
        }
        Pagination pagination=questionService.getPage(page);
        model.addAttribute("pagination",pagination);
        return "index";
    }
}
