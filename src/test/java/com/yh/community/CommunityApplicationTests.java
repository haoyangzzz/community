package com.yh.community;

import com.yh.community.model.Question;
import com.yh.community.model.User;
import com.yh.mapper.QuestionMapper;
import com.yh.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommunityApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Test
    public void contextLoads() {
        User user = userMapper.findByToken("128b2c2a-a10b-4945-8974-07a68fa972be");
        System.out.println(user);
    }

//    @Test
//    public void testQuestion(){
//
//        List<Question> lists = questionMapper.list();
//        for (Question list : lists) {
//            System.out.println(list);
//        }
//    }


    @Test
    public void testFindById(){
        User user = userMapper.findById(8);
        System.out.println(user);
    }

    @Test
    public void testGetById(){
        Question question = questionMapper.getById(8);
        System.out.println(question);
    }


}
