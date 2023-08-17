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
    //认证白名单 (允许匿名访问)
    public static final String[] WEB_WHITELIST = {
//            "/webjars/**",
//            "/druid/**",
//            "/",
//            "/swagger-ui/**",
//            "/swagger-resources/**",
//            "/*/api-docs/**",
            "/sys-login/captcha.jpg",
            "/sys-login/login",
            "/actuator/**"

    };

}
