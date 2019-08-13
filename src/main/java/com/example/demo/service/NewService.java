package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class NewService {
    private String path = "http://toutiao-ali.juheapi.com/toutiao/index";
    private String appcode = "d9b5651cfc724c5caaadb275544cf95f";
    @Autowired
    private RestTemplate restTemplate;

    public String getNews(String type)
    {
        HttpHeaders headers =new HttpHeaders();
        headers.add("Authorization","APPCODE " + appcode);
        HttpEntity<String>  httpEntity=new HttpEntity<>(null,headers);//添加请求头和请求体
        ResponseEntity<String> responseEntity=restTemplate.exchange
                (path+"?type="+type, HttpMethod.GET,httpEntity,String.class);
        return responseEntity.getBody();
    }
}
