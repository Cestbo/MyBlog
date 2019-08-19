package com.example.demo.mapper;


import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {

    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,bio,avatar_url) " +
            "values(#{name},#{account_id},#{token},#{gmt_create},#{gmt_modified},#{bio},#{avatar_url})")
    int insert(User user);

    @Select("select * from user where token=#{token}")
    User getUserByToken(@Param("token") String user_token);

    @Select("select * from user where id=#{id}")
    User getUserByid(int id);

    @Select("select * from user where account_id=#{account_id}")
    User getUserByAccountId(String account_id);
}
