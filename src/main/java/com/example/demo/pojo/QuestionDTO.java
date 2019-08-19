package com.example.demo.pojo;

import lombok.Data;

@Data
public class QuestionDTO extends Question {
    private User user;
}
