package com.example.demo;

import com.example.demo.exception.ErrorResponse;
import com.example.demo.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ErrorResponse myErrorHandler(MyException e)
    {
        return new ErrorResponse(e.getCode(),e.getMessage());
    }
}
