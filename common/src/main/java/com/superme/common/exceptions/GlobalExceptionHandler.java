package com.superme.common.exceptions;


import com.superme.common.beans.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述: 对全局异常的封装
 * 作者: yanruizhi
 * 时间: 2023/6/29 15:20
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //参数异常处理
    @ExceptionHandler(value = ParameterException.class)
    @ResponseBody
    public Result handleException(ParameterException e, HttpServletRequest request) {
        log.error("url {}, msg {}", request.getRequestURL(), e);
        return Result.error(e.getCode(), e.getMsg());
    }

    //未知异常处理
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handleException(Exception e, HttpServletRequest request) {
        log.error("url {}, msg {}", request.getRequestURL(), e);
        return Result.error("-1",e.getMessage());
    }

    //登录异常处理
    @ExceptionHandler(value = LoginException.class)
    @ResponseBody
    public Result handleException(LoginException e, HttpServletRequest request) {
        log.error("url {}, msg {}", request.getRequestURL(), e);
        return Result.error("-1",e.getMessage());
    }
}
