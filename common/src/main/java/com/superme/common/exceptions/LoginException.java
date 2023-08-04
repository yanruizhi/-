package com.superme.common.exceptions;

import com.superme.common.enums.ExceptionCodeEnum;

/**
 * 描述: 登录异常
 * 作者: yanruizhi
 * 时间: 2023/7/4 16:25
 */

public class LoginException extends RuntimeException{
    private String code;
    private String msg;

    public LoginException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public LoginException(String msg) {
        super(msg);
        this.code = ExceptionCodeEnum.AUTHENTICATION_FAILED.getCode();
        this.msg = msg;
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
