package com.hsy.servicebase.exception;

import com.hsy.commonutil.result.Result;
import com.hsy.commonutil.result.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.build(500,"服务端错误");
    }
    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody //为了返回数据
    public Result error(ArithmeticException e) {
        e.printStackTrace();
        return Result.build(500,"执行了ArithmeticException异常处理..");
    }

    //自定义异常
    @ExceptionHandler(GuliException.class)
    @ResponseBody //为了返回数据
    public Result error(GuliException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.build(e.getCode(),e.getMessage());
    }

}
