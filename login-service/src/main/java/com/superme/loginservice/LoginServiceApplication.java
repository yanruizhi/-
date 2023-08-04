package com.superme.loginservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;

@SpringBootApplication(scanBasePackages = {"com.superme.common","com.superme.loginservice"})
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)//组止注册jedisPool时报错"MBean已存在"
public class LoginServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginServiceApplication.class, args);
    }

}
