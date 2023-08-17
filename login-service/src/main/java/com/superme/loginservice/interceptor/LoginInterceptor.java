package com.superme.loginservice.interceptor;

import com.superme.loginservice.enums.Constant;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器,判断是否登录
 * 作者: yanruizhi
 * 时间: 2023/8/17 16:01
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private Jedis jedis;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //校检是否登录
        String token = request.getHeader("token");
        if (token == null || token.equals("")) {
            throw new LoginException("请先登录");
        }
        Boolean exists = jedis.exists(token);
        if (!exists) {
            throw new LoginException("登录信息已过期,请重新登录");
        }
        return true;
    }
}
