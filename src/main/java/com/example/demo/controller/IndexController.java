package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/")
    public String index(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("user_token".equals(cookie.getName()))
            {
                User user=userMapper.getUserByToken(cookie.getValue());
                if(user!=null)
                    request.getSession().setAttribute("user",user);
                break;
            }

        }
        return "index";
    }
}
