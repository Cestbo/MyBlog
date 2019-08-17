package com.example.demo.mapper;

import com.example.demo.pojo.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {


    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag)" +
            " values(#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{comment_count},#{view_count}," +
            "#{like_count},#{tag}) ")
    void insert(Question question);
}
