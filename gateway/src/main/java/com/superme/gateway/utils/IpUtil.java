package com.superme.gateway.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * 获取用户访问ip地址
 */
@Slf4j
public class IpUtil {
    public static void getIpAddress(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        //        InetAddress address = request.getRemoteAddress().getAddress();
        //        InetSocketAddress remoteAddress = request.getRemoteAddress();

        List<String> list = headers.get("x-forwarded-for");

        String ip;
        if (ObjectUtils.isEmpty(list)) {
            ip = "localhost";
        } else {
            ip = list.get(0);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = Objects.requireNonNull(headers.get("Proxy-Client-IP")).get(0);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = Objects.requireNonNull(headers.get("WL-Proxy-Client-IP")).get(0);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = Objects.requireNonNull(headers.get("HTTP_CLIENT_IP")).get(0);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = Objects.requireNonNull(headers.get("HTTP_X_FORWARDED_FOR")).get(0);
        }
        //        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        //            ip =request.getRemoteAddress().;
        //        }
        // 获取到多个ip时取第一个作为客户端真实ip
        if (!ObjectUtils.isEmpty(ip) && ip.contains(",")) {
            String[] ipArray = ip.split(",");
            if (ArrayUtils.isNotEmpty(ipArray)) {
                ip = ipArray[0];
            }
        }

        log.info("来访时间: [{}], 来访主机ip: [{}]", DateUtil.parseDate(new Date()), ip);
    }
}
