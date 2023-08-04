package com.superme.common.exceptions;


import com.superme.common.enums.ExceptionCodeEnum;

/**
 * 描述: 参数异常类
 * 作者: yanruizhi
 * 时间: 2023/5/31 16:53
 */

public class ParameterException extends RuntimeException {
    private String code;
    private String msg;

    public ParameterException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public ParameterException(String msg) {
        super(msg);
        this.code = ExceptionCodeEnum.PARAMETER_NULL.getCode();
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
