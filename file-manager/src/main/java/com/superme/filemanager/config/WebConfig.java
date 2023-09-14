package com.superme.filemanager.config;

import com.superme.filemanager.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 描述: WebConfig,请求拦截 过滤
 * 作者: yanruizhi
 * 时间: 2023/7/19 10:37
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;

    //允许跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }

    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loginInterceptor);//添加登录拦截器,过滤未登录用户

    }


}
