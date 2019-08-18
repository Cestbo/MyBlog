package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.AccessToken;
import com.example.demo.pojo.GithubUser;
import com.example.demo.pojo.User;
import com.example.demo.provider.GithubAutoPro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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

    /*报“no beans of 'UserMapper'type found”,这是因为mybatis启动器不是spring
    官方出的，spring会误认为没有注入容器，实际已经注入了*/
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code")String code, @RequestParam(name = "state") String state
    ,  HttpServletResponse response)
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
        GithubUser githubUser=githubAutoPro.getUserInfo(token);
        System.out.println(githubUser.getBio());
        if(githubUser!=null)
        {
            //将用户信息添加到数据库，通过添加cookie：user_token获取用户状态
            User user=new User();
            user.setAccountId(githubUser.getId().toString());
            user.setName(githubUser.getName());
            String user_token=UUID.randomUUID().toString();
            user.setToken(user_token);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(System.currentTimeMillis());
            user.setBio(githubUser.getBio());
            user.setAvatar_url(githubUser.getAvatar_url());
            userMapper.insert(user);
            response.addCookie(new Cookie("user_token",user_token));
        }
        return "redirect:/";    //"redirect:index"无法重定向到index，这个相当于路径访问，无法访问到template下的html文件
    }

    @RequestMapping("/logout")
    public String logout(HttpServletResponse response,HttpServletRequest request)
    {
        if(request.getSession().getAttribute("user")!=null)
        {
            request.getSession().removeAttribute("user");
        }
        //删掉cookie：user_token，思路就是替换原来的cookie,并设置它的生存时间为0
        Cookie user_cookie=new Cookie("user_token",null);
        user_cookie.setMaxAge(0);
        response.addCookie(user_cookie);
        return "redirect:/";
    }
}
