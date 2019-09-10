package com.example.demo.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    private Integer code;
    private String msg;

    public ErrorResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
