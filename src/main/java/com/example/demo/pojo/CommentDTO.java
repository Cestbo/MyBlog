package com.example.demo.pojo;

import lombok.Data;

@Data
public class CommentDTO extends Comment {
    private User user;
}
