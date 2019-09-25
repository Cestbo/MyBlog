package com.example.demo.mapper;

import com.example.demo.pojo.Question;
import com.example.demo.pojo.QuestionDTO;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;


@Mapper
public interface QuestionMapper {


    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag)" +
            " values(#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{comment_count},#{view_count}," +
            "#{like_count},#{tag}) ")
    void insert(Question question);

    @Select("select * from question order by gmt_modified desc limit #{offset},#{size}")
    ArrayList<QuestionDTO> getPage(@Param("offset") int offset, @Param("size") int size);

    @Select("select count(id) from question")
    int count();

    @Select("select *from question where creator=#{userId} limit #{offset},#{size}")
    ArrayList<QuestionDTO> getPageByUser(@Param("userId") int userId ,@Param("offset") int offset, @Param("size") int size);

    @Select(" select count(id) from question where creator=#{userId}")
    int countByUserId(@Param("userId") int useId);

    @Select("select *from question where id=#{id}")
    QuestionDTO getQuesById(int id);

    @Update("update question set title=#{title},description=#{description}," +
            "tag=#{tag},gmt_modified=#{gmt_modified} where id=#{id}")
    void update(Question question);

    //单独拿出阅读数、评论数和点赞数更新，以免并发时造成数据错误
    @Update("update question set view_count=view_count+1 where id=#{id}")
    int updateView_count(Integer id);

    @Update("update question set like_count=like_count+1 where id=#{id}")
    int updateLike_count(Integer id);

    @Update("update question set comment_count=comment_count+1 where id=#{id}")
    void updateComment_count(Long id);
}
