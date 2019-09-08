package com.example.demo;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/*全局异常处理*/
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ModelAndView errorHandler(RuntimeException e)
    {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", e.getMessage());
        mav.setViewName("error");
        return mav;
    }
}
