package com.example.demo.service;

import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.Pagination;
import com.example.demo.pojo.QuestionDTO;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @Value("${page.question.num}")
    private int size;

    public Pagination getPage(int page)
    {
        ArrayList<QuestionDTO> questionDTOS=questionMapper.getPage(size*(page-1),size);
        for (QuestionDTO questionDTO : questionDTOS) {
            User user=userMapper.getUserByid(questionDTO.getCreator());
            questionDTO.setUser(user);
        }
        Pagination pagination=new Pagination();
        pagination.setQuestionDTOS(questionDTOS);
        pagination.setPage(page);

        //计算总页数
        int count=questionMapper.count();
        int totalPages;
        if(count%size>0)
            totalPages=count/size+1;
        else
            totalPages=count/size;

        pagination.setEndPage(totalPages);
        pagination.setPaginaton(totalPages);
        return pagination;
    }
}
