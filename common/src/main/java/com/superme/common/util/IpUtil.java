package com.superme.common.util;

import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取用户访问ip地址
 */
public class IpUtil {
    public static String getIpAddress(HttpServletRequest request) {  
        String ip = request.getHeader("x-forwarded-for");  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_CLIENT_IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }
        // 获取到多个ip时取第一个作为客户端真实ip
        if (!ObjectUtils.isEmpty(ip) && ip.contains(",")) {
        	String[] ipArray = ip.split(",");
        	if (ArrayUtils.isNotEmpty(ipArray)) {
        		ip = ipArray[0];
        	}
        }
        return ip;  
    }  
}
