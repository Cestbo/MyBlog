package com.example.demo.controller;

import com.example.demo.pojo.AccessToken;
import com.example.demo.pojo.GithubUser;
import com.example.demo.provider.GithubAutoPro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {
    @Autowired
    private GithubAutoPro githubAutoPro;

    //从.properties文件获取GitHub授权数据
    @Value("${github.client_id}")
    private String client_id;
    @Value("${github.client_secret}")
    private String client_secret;
    @Value("${github.redirect_url}")
    private String redirect_url;

    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code")String code, @RequestParam(name = "state") String state
    , HttpServletRequest request)
    {
        AccessToken accessToken=new AccessToken();
        /*这些数据硬编码在代码里不方便以后更改，改为.properties文件配置*/
        //accessToken.setClient_id("e5361a72c6d25c61b247");
        accessToken.setClient_id(client_id);
        accessToken.setClient_secret(client_secret);
        accessToken.setCode(code);
        accessToken.setRedirect_uri(redirect_url);
        accessToken.setState(state);
        String token=githubAutoPro.getAccessToken(accessToken);
        GithubUser user=githubAutoPro.getUserInfo(token);
        System.out.println(user.getBio());
        if(user!=null)
        {
            request.getSession().setAttribute("user",user);
        }
        return "redirect:/";    //"redirect:index"无法重定向到index，这个相当于路径访问，无法访问到template下的html文件
    }
}
