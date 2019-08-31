package com.example.demo.mapper;


import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.*;


@Mapper
public interface UserMapper {

    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,bio,avatar_url) " +
            "values(#{name},#{account_id},#{token},#{gmt_create},#{gmt_modified},#{bio},#{avatar_url})")
    int insert(User user);

    @Select("select * from user where token=#{token}")
    User getUserByToken(@Param("token") String user_token);

    @Select("select * from user where id=#{id}")
    User getUserById(int id);

    @Select("select * from user where account_id=#{account_id}")
    User getUserByAccountId(String account_id);

    //不会更新id和account_id，唯一标识
    @Update("update user set name=#{name},token=#{token},gmt_modified=#{gmt_modified},bio=#{bio}," +
            "avatar_url=#{avatar_url}")
    void update(User user);
}
