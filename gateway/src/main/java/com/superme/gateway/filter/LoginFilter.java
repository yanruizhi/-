package com.superme.gateway.filter;

import com.superme.gateway.beans.Result;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import redis.clients.jedis.Jedis;


import javax.annotation.Resource;
import javax.security.auth.login.LoginException;
import java.util.List;

/**
 * 登录过滤器
 * 作者: yanruizhi
 * 时间: 2023/8/17 14:15
 */
@Component
public class LoginFilter implements GlobalFilter, Ordered {

    @Resource
    private Jedis jedis;

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //网关拦截


        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
