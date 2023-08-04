package com.superme.loginservice.service;

import com.superme.common.beans.Result;
import com.superme.loginservice.pojo.Entity.User;
import com.superme.loginservice.pojo.qo.LoginUser;

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
    Result<Object> login(LoginUser user);

    Long getUserIdByUsername(String username);
    /**
     * 生成验证码
     */
    void verifyCode(HttpServletRequest request, HttpServletResponse response);
}
