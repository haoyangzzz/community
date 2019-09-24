package com.yh.community.controller;

import com.yh.community.dto.AccessTokenDTO;
import com.yh.community.dto.GithubUser;
import com.yh.community.model.User;
import com.yh.community.provider.GithupProvider;
import com.yh.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GithupProvider githupProvider;
    @Value("${github.client.id}") //通过配置application.properties 直接获得里面的值
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUrl;

    @GetMapping("/callback")                    //通过域名获得的code
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state,
                           HttpServletResponse response)
    {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUrl);
        accessTokenDTO.setState(state);
        String accessToken = githupProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githupProvider.getUser(accessToken);
        //System.out.println(githubUser.getName());

        if(githubUser != null){
            User user = new User();
            String token = UUID.randomUUID().toString(); //登录成功后，获取到用户信息然后生成一个token，将它写入到cookie
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            response.addCookie(new Cookie("token", token));//response设置的cookie


            //登录成功，创建cookie和session
            //request.getSession().setAttribute("githubUser", user); //获得一个账户 放置信息
            return "redirect:/";//重定向的方式在跳转回index而不是渲染 登录成功之后要有一个跳转的过程
        }else {
            //登录失败,重新登录
            return "redirect:/";
        }
    }
}
