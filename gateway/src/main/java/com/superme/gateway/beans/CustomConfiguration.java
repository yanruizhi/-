package com.superme.gateway.beans;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * 自定义配置
 * 作者: yanruizhi
 * 时间: 2023/9/15 9:16
 */
@Component
@ConfigurationProperties(prefix = "customconfiguration")
@Data
public class CustomConfiguration {

    /**
     * 不需要登录访问的接口集合
     */
    private Set<String> whitelist;


}
