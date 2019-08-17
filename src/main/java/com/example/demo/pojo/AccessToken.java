package com.example.demo.pojo;

import lombok.Data;

@Data
public class AccessToken {
    private String client_id;
    private String client_secret;
    private String code,redirect_uri,state;

}
