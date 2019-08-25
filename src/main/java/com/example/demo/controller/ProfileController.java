package com.example.demo.controller;

import com.example.demo.pojo.Pagination;
import com.example.demo.pojo.User;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;
    @RequestMapping("/profile/{action}")
    public String profile(Model model, @RequestParam(name = "page",defaultValue = "1") int page ,
                          @PathVariable(name = "action") String action,
                          HttpServletRequest request)
    {

        if("questions".equals(action))
        {
            User user=(User) request.getSession().getAttribute("user");
            if (user==null)
                return "redirect:/";
            Pagination pagination=questionService.getPageByUser(page,user.getId());
            model.addAttribute("pagination",pagination);
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的问题");
        }
        if("replies".equals(action))
        {
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }

        return "profile";
    }
}
