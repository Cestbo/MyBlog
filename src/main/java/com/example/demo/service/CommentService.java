package com.example.demo.service;

import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    /*添加事务，操作失败时回滚，防止添加了评论而没有增加评论数*/
    @Transactional
    public void insert(Comment comment, Long parent_id) {
        commentMapper.insert(comment);
        questionMapper.updateComment_count(parent_id);
    }
}
