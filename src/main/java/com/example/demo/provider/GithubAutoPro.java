package com.example.demo.provider;

import com.example.demo.pojo.AccessToken;
import com.example.demo.pojo.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class GithubAutoPro {
    @Autowired
    private RestTemplate restTemplate;
    public String getAccessToken(AccessToken accessToken)
    {
        MultiValueMap<String,String> para=new LinkedMultiValueMap<>();
        para.add("client_id",accessToken.getClient_id());
        para.add("client_secret",accessToken.getClient_secret());
        para.add("code",accessToken.getCode());
        para.add("redirect_uri",accessToken.getRedirect_uri());
        para.add("state",accessToken.getState());
        HttpEntity<MultiValueMap<String,String>> request=new HttpEntity<>(para,null);
        ResponseEntity<String> responseEntity=restTemplate.exchange
                ("https://github.com/login/oauth/access_token", HttpMethod.POST,request
        ,String.class);
        return responseEntity.getBody();

    }

    public GithubUser getUserInfo(String token)
    {
//        HttpHeaders headers=new HttpHeaders();
//        headers.add("Authorization","token "+token);
//        HttpEntity<String> request=new HttpEntity<>(null,headers);
        String url="https://api.github.com/user?"+token;
        ResponseEntity<GithubUser> responseEntity=restTemplate.getForEntity(url,
            GithubUser.class);
        return responseEntity.getBody();
    }
}