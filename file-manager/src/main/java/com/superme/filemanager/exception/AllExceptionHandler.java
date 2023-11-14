package com.superme.filemanager.exception;

import com.superme.common.beans.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
    public Result<Object> handleException(Exception e) {
        return Result.error("未知异常,请联系管理员");
    }

    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFileSize;

    @Value("${spring.servlet.multipart.max-request-size}")
    private String maxRequestSize;

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result<Object> handleException(MaxUploadSizeExceededException e) {
        log.info("上传文件大小超过限制,最大文件不超过{}; 每批次最大文件不超过{}; ", maxFileSize, maxRequestSize);
        log.info("错误信息: {}",e.getMessage());
        return Result.error("上传文件大小超过限制,最大文件不超过" + maxFileSize + "; 每批次最大文件不超过" + maxRequestSize);
    }
}
