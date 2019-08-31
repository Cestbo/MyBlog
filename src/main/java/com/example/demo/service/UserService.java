package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.GithubUser;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void UpdateOrAddUser(User user,String account_id)
    {
        //判断数据库中是否存在该用户，用account_id判断
        User db_user=userMapper.getUserByAccountId(account_id);
        //不存在添加
        if(db_user==null)
        {
            userMapper.insert(user);
        }
        //存在更新
        else {
            userMapper.update(user);
        }
    }
}
