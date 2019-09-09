package com.example.demo.pojo;

import lombok.Data;

@Data
public class Comment {
    private Long id;
    private Long parent_id;  //评论的对象
    private Integer type;    //1表示评论的问题，2表示回复的别人的评论
    private Long commentator;
    private Long gmt_create;
    private Long gmt_modified;
    private Long like_count;
    private String content;
}
