package com.example.demo.controller;

import com.example.demo.exception.ErrorType;
import com.example.demo.exception.MyException;
import com.example.demo.pojo.Comment;
import com.example.demo.pojo.User;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    /*前端传过来的：
    * parent_id,content,type*/
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    @ResponseBody
    public Map comment(@RequestBody Comment comment, HttpServletRequest request)
    {
        User user = (User) request.getSession().getAttribute("user");
        if(user==null)
        {
            throw new MyException(ErrorType.NOT_LOGIN);
        }
        if(comment==null)
        {
            throw new MyException(ErrorType.NO_COMMENT);
        }
        comment.setCommentator((long) user.getId());
        comment.setGmt_create(System.currentTimeMillis());
        comment.setGmt_modified(System.currentTimeMillis());
        comment.setLike_count((long) 0);
        commentService.insert(comment,comment.getParent_id());
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg","回复成功");
        map.put("code",200);
        return map;

    }

    /*评论点赞*/
    @ResponseBody
    @RequestMapping(value = "/comment_like",method = RequestMethod.POST)
    public int comment_like(int id)
    {
        return commentService.like(id);
    }
}
