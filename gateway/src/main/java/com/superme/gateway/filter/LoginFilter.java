package com.superme.gateway.filter;


import com.alibaba.fastjson.JSONObject;
import com.superme.gateway.beans.CustomConfiguration;
import com.superme.gateway.utils.IpUtil;
import lombok.SneakyThrows;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import redis.clients.jedis.Jedis;


import javax.annotation.Resource;
import java.util.ArrayList;
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

    private static final String AUTHORIZE_TOKEN = "token";

    private static AntPathMatcher matcher = new AntPathMatcher();



    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //网关拦截
        ServerHttpRequest request = exchange.getRequest();
        //记录ip
        IpUtil.getIpAddress(request);

        // 不需要拦截的url直接放行
        if(needLogin(request.getPath().toString())){
            return chain.filter(exchange);
        }

        String accessToken = request.getHeaders().getFirst(AUTHORIZE_TOKEN);
        if (ObjectUtils.isEmpty(accessToken) || !jedis.exists(accessToken)) {
            return loginResponse(exchange);
        }
        //放行
        return chain.filter(exchange);

        //        exchange.getResponse().setStatusCode(HttpStatus.);
        //设置拦截
        //        return exchange.getResponse().setComplete();
    }
    public static Mono<Void> loginResponse(ServerWebExchange exchange) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("code", 401);
        resultJson.put("message", "登录信息过期,请重新登录");
        ServerHttpResponse response = exchange.getResponse();
        byte[] bytes = JSONObject.toJSONBytes(resultJson);
        response.getHeaders().add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Flux.just(buffer));
    }


    @Resource
    public CustomConfiguration configuration;

    public  boolean needLogin(String uri){
        // 登录认证白名单
        List<String> uriList = new ArrayList<>();
        System.out.println("routingWhitelist = " + configuration.getWhitelist());
        //        uriList = Arrays.asList(whitelist);
        //TODO 放入配置文件中读取
        uriList.add("/login/doLogin");//登录
        uriList.add("/login/verifyCode");//获取验证码

//        System.out.println(whitelist);

        for (String pattern : uriList) {
            if (matcher.match(pattern, uri)) {
                // 不需要拦截
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 2;
    }


}
