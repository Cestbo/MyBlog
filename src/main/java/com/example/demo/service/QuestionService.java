package com.example.demo.service;

import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.Question;
import com.example.demo.pojo.QuestionDTO;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public ArrayList<QuestionDTO> list()
    {
        ArrayList<QuestionDTO> questionDTOS=questionMapper.getAll();
        for (QuestionDTO questionDTO : questionDTOS) {
            User user=userMapper.getUserByid(questionDTO.getCreator());
            questionDTO.setUser(user);
        }

        return questionDTOS;
    }
}
