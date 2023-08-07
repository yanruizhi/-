package com.superme.common.enums;

/**
 * 描述: 异常状态码枚举类
 * 作者: yanruizhi
 * 时间: 2023/5/31 16:59
 */
public enum ExceptionCodeEnum {
    //参数异常10开头
    PARAMETER_NULL("1001","参数为空"),


    //未查询到数据 20开头
    DATA_NULL("2001","该条数据不存在或已被其他管理员修改"),
    DATA_NULL_LIST("2002","未查询到数据"),

    //登录异常 30开头
    AUTHENTICATION_FAILED("3001", "认证失败"),
    ACCESS_DENIED("3002", "鉴权异常"),

    //文件上传下载异常
    FILE_EXCEPTION("4000", "文件IO异常"),
    ;

    private String code;
    private String message;

    ExceptionCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
