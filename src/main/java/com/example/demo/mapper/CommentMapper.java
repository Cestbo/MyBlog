package com.example.demo.mapper;

import com.example.demo.pojo.Comment;
import com.example.demo.pojo.CommentDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("insert into comment(parent_id,type,commentator,gmt_create,gmt_modified,like_count,content) " +
            "values(#{parent_id},#{type},#{commentator},#{gmt_create},#{gmt_modified},#{like_count},#{content})")
    void insert(Comment comment);

    @Select("select * from comment where parent_id=#{id}")
    List<CommentDTO> getByParentId(Integer id);

    @Update("update comment set like_count=like_count+1 where id=#{id}")
    void like(int id);

    @Select("select like_count from comment where id=#{id}")
    int getLike_id(int id);
}
