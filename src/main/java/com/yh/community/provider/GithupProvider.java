package com.yh.community.provider;

import com.alibaba.fastjson.JSON;
import com.yh.community.dto.AccessTokenDTO;
import com.yh.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import javax.sound.midi.Soundbank;
import java.io.IOException;

@Component
public class GithupProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){//模拟post请求   携带code发送到github返回access_token
         MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            System.out.println(token);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken){//模拟解析token的get方法
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
