package com.superme.loginservice.controller;

import com.superme.common.beans.Result;
import com.superme.loginservice.pojo.Entity.User;
import com.superme.loginservice.pojo.qo.LoginUser;
import com.superme.loginservice.service.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录管理
 * 作者: yanruizhi
 * 时间: 2023/7/18 16:42
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @Resource
    private LoginService loginService;


    /**
     * 生成验证码
     */
    @GetMapping("verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        loginService.verifyCode(request, response);
    }
    /**
     * 登录
     */
    public Result<Object> login(LoginUser user) {
       return loginService.login(user);
    }

}
