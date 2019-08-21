package com.example.demo.mapper;

import com.example.demo.pojo.Question;
import com.example.demo.pojo.QuestionDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;


@Mapper
public interface QuestionMapper {


    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag)" +
            " values(#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{comment_count},#{view_count}," +
            "#{like_count},#{tag}) ")
    void insert(Question question);

    @Select("select * from question limit #{offset},#{size}")
    ArrayList<QuestionDTO> getPage(@Param("offset") int offset, @Param("size") int size);

    @Select("select count(id) from question")
    int count();

}
