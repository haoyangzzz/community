package com.yh.community.service;

import com.yh.community.model.Question;
import com.yh.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class questionService {
    @Autowired
    private QuestionMapper questionMapper;

    public List<Question> list(Integer page,Integer size){

        Integer offset = size * (page - 1);
        return questionMapper.list(offset,size);
    }
    public List<Question> list(Integer userId,Integer page,Integer size){
        return questionMapper.listByUserId(userId, page, size);
    }

    public Question getById(Integer id) {

        return questionMapper.getById(id);
    }
}
