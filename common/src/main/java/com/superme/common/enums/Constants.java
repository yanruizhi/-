package com.superme.common.enums;

/**
 * 描述: 常量类，包含应用程序中使用的固定字符串值。
 * 作者: yanruizhi
 * 时间: 2023/7/4 10:51
 */
public class Constants {


    //跨域预检接口 (任何人都允许访问)
    public static final String[] CROSS_WHITELIST = {
            "/webjars/**",
            "/druid/**",
            "/",
            "/swagger-ui/**",
            "/swagger-ui.html/**",
            "/swagger-resources/**",
            "/*/api-docs/**",
            "/sys-login/captcha.jpg",
            "/sys-login/login"

    };
    //web拦截白名单
    public static final String[] WHITE_LIST = {
            "/login-service/login/verifyCode",
            "/login/doLogin"

    };
    public static final String TOKEN = "token";    //用户登录状态请求头名称
    public static final Integer TIME_ONE_DAY = 86400; //一天的秒数

}
