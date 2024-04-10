package com.superme.gateway.exception;

import com.superme.gateway.beans.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 描述: TODO
 * 作者: yanruizhi
 * 时间: 2023/9/14 16:31
 */
@RestControllerAdvice
@Slf4j
public class AllExceptionHandler {
   @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
       log.error("未知异常,请及时处理 {}", e.getMessage());
       return Result.error(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result handleException(RuntimeException e) {
        log.error("未知异常,请及时处理 {}", e.getMessage());
        return Result.error(e.getMessage());
    }
}
