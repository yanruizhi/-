package com.superme.loginservice.pojo.qo;

import lombok.Data;

/**
 * 描述: LoginUser
 * 作者: yanruizhi
 * 时间: 2023/7/18 16:51
 */
@Data
public class LoginUser {
    private String username;//用户名
    private String password;//密码
    private String vcode;//验证码
    private String email;//邮箱
    private String phone;//手机号
}
