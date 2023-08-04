package com.superme.common.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述: 返回结果的封装类
 * 作者: yanruizhi
 * 时间: 2023/5/31 16:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private String code;
    private boolean success;
    private String message;
    private Object data;
    /** 时间戳 */
    private long timestamp = System.currentTimeMillis();


    public  Result<T> success(String message) {
        this.message = message;
        this.code = "200";
        this.success = true;
        return this;
    }

    public  Result<T> success() {
        this.message = message;
        this.code = "200";
        this.success = true;
        return this;
    }


    public static<T> Result<T> OK(String msg, T data) {
        Result<T> r = new Result<T>();
        r.setSuccess(true);
        r.setCode("200");
        r.setMessage(msg);
        r.setData(data);
        return r;
    }

    public static<T> Result<T> OK(T data) {
        Result<T> r = new Result<T>();
        r.setSuccess(true);
        r.setCode("200");
        r.setData(data);
        r.setMessage("success");
        return r;
    }

    public static<T> Result<T> OK() {
        Result<T> r = new Result<T>();
        r.setSuccess(true);
        r.setCode("200");
        r.setData(null);
        r.setMessage("success");
        return r;
    }

    public static Result<Object> error(String code, String msg) {
        Result<Object> r = new Result<Object>();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }
    public static Result<Object> error(String msg) {
        Result<Object> r = new Result<Object>();
        r.setCode("-1");
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }


}
