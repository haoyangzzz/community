package com.yh.community.controller;

import com.yh.community.model.Question;
import com.yh.community.model.User;
import com.yh.community.service.questionService;
import com.yh.mapper.QuestionMapper;
import com.yh.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private questionService questionService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Integer id, Model model){
        Question question = questionService.getById(id);

        model.addAttribute("question", question);
        return "question";
    }
}
