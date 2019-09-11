package com.example.demo.service;

import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentMapper commentMapper;

    @Value("${page.question.num}")
    private int size;

    public Pagination getPage(int page)
    {
        ArrayList<QuestionDTO> questionDTOS=questionMapper.getPage(size*(page-1),size);
        for (QuestionDTO questionDTO : questionDTOS) {
            User user=userMapper.getUserById(questionDTO.getCreator());
            questionDTO.setUser(user);
        }
        Pagination pagination=new Pagination();
        pagination.setQuestionDTOS(questionDTOS);
        pagination.setPage(page);

        //计算总页数
        int count=questionMapper.count();
        int totalPages;
        if(count%size>0)
            totalPages=count/size+1;
        else
            totalPages=count/size;
        //如果没数据，总页数为1
        if (totalPages==0)
            totalPages=1;
        pagination.setEndPage(totalPages);
        pagination.setPaginaton(totalPages);
        return pagination;
    }

    public Pagination getPageByUser(int page,int userId) {

        ArrayList<QuestionDTO> questionDTOS=questionMapper.getPageByUser(userId,size*(page-1),size);

        Pagination pagination=new Pagination();
        pagination.setQuestionDTOS(questionDTOS);
        pagination.setPage(page);

        //计算总页数
        int count=questionMapper.countByUserId(userId);
        int totalPages;
        if(count%size>0)
            totalPages=count/size+1;
        else
            totalPages=count/size;

        pagination.setEndPage(totalPages);
        pagination.setPaginaton(totalPages);
        return pagination;
    }

    public QuestionDTO getQuesDTOById(int id) {
        QuestionDTO questionDTO=questionMapper.getQuesById(id);
        if(questionDTO==null)
        {
            throw new RuntimeException("不存在该问题");
        }
        //根据question的creator找到user
        User user=userMapper.getUserById(questionDTO.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void insertOrUpdate(Question question, String id) {
        if(id==null || id=="")
        {
            questionMapper.insert(question);
        }
        //记录，需要id确定哪条记录
        else {
            question.setId(Integer.valueOf(id));
            questionMapper.update(question);
        }

    }


    public void incView_count(int id) {
        if (questionMapper.updateView_count(id)<=0)
            throw new RuntimeException("更新阅读数出错");

    }

    //查询问题中的评论
    public List<CommentDTO> getCommentsByParentId(Integer id)
    {
        ArrayList<CommentDTO> comments=new ArrayList<>();
        List<CommentDTO> commentList = commentMapper.getByParentId(id);
        for (CommentDTO commentDTO : commentList) {
            if (commentDTO.getType()==1)
            {
                User user = userMapper.getUserById(Math.toIntExact(commentDTO.getCommentator()));
                commentDTO.setUser(user);
                comments.add(commentDTO);
            }
        }
        return comments;
    }
}
