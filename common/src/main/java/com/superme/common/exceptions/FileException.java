package com.superme.common.exceptions;

import com.superme.common.enums.ExceptionCodeEnum;

/**
 * 描述: TODO
 * 作者: yanruizhi
 * 时间: 2023/8/7 11:33
 */
public class FileException extends RuntimeException {
    private String code;
    private String msg;

    public FileException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public FileException(String msg) {
        super(msg);
        this.code = ExceptionCodeEnum.AUTHENTICATION_FAILED.getCode();
        this.msg = msg;
    }

    public FileException() {
        this.code = ExceptionCodeEnum.FILE_EXCEPTION.getCode();
        this.msg = ExceptionCodeEnum.FILE_EXCEPTION.getMessage();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
