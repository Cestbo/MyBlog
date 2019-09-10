package com.example.demo.exception;

import lombok.Getter;

@Getter
public class MyException extends RuntimeException {
    private Integer code;

    public MyException(ErrorType errorType) {
        super(errorType.getMessage());
        this.code = errorType.getCode();

    }
}
