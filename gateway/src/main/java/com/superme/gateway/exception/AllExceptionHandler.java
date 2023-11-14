package com.superme.gateway.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * 描述: TODO
 * 作者: yanruizhi
 * 时间: 2023/9/14 16:31
 */
@RestControllerAdvice
@Slf4j
public class AllExceptionHandler {
   @ExceptionHandler(Exception.class)
    public void handleException(Exception e) {
       log.error("未知异常,请及时处理 {}", e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public void handleException(RuntimeException e) {
        log.error("未知异常,请及时处理 {}", e.getMessage());
    }
}
