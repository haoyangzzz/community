package com.yh.community.controller;

import com.yh.community.dto.AccessTokenDTO;
import com.yh.community.dto.GithubUser;
import com.yh.community.provider.GithupProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

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
                           @RequestParam("state") String state)
    {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUrl);
        accessTokenDTO.setState(state);
        String accessToken = githupProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githupProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
