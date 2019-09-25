package com.example.demo.exception;

import lombok.Getter;
/*管理各种异常*/
@Getter
public enum ErrorType {
    NOT_LOGIN(2000,"未登录，请先登录"),
    NO_COMMENT(2001,"评论不能为空");
    private Integer code;
    private String message;

    ErrorType(int i, String s) {
        code=i;
        message=s;
    }
}
