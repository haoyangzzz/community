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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {//判断用户是否登录

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private questionService questionService;
    @GetMapping("/")
    public String index(HttpServletRequest request,Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size
                        ){

        Integer pageSum = 0;
        Integer total = questionMapper.total();
        if(total % size != 0){
            pageSum = total / size +1;
        }else{
            pageSum = total / size;
        }
        model.addAttribute("pageSum", pageSum);

//        Integer offset = size * (page - 1);
//        List<Question> questionList = questionMapper.list(offset,size);

        List<Question> questionList = questionService.list(page, size);
        model.addAttribute("questions", questionList);
         return "index";
    }
}
