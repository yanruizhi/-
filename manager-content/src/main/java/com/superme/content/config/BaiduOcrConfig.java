package com.superme.content.config;

import com.baidu.aip.ocr.AipOcr;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 描述: 百度Ocr文字识别配置信息
 * 作者: yanruizhi
 * 时间: 2023/11/22 11:55
 */
@ConfigurationProperties(prefix = "ocr.config")
@Component
@Data
public class BaiduOcrConfig {
    /**
     * 应用程序id
     */
    private String appId;
    /**
     * api密钥
     */
    private String apiKey;
    /**
     * 密钥
     */
    private String secretKey;

    @Bean
    public AipOcr getAipOcr() {
        return new AipOcr(appId, apiKey, secretKey);
    }
}
