package com.superme.loginservice.service;

import com.superme.common.beans.Result;
import com.superme.loginservice.pojo.qo.LoginUserQo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述: LoginService
 * 作者: yanruizhi
 * 时间: 2023/7/18 16:43
 */
public interface LoginService {
    /**
     * 登录
     */
    Result<Object> login(LoginUserQo user);

    Long getUserIdByUsername(String username);
    /**
     * 生成验证码
     */
    void verifyCode(HttpServletRequest request, HttpServletResponse response);

    /**
     * 登出
     */
    Result<Object> logout(HttpServletRequest request, HttpServletResponse response);


}
