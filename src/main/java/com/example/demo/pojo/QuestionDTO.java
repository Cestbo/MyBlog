package com.example.demo.pojo;

import lombok.Data;
/*
* 封装问题，便于展示问题列表*/
@Data
public class QuestionDTO extends Question {
    private User user;
}
