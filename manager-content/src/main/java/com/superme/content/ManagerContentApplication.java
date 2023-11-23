package com.superme.content;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.superme.content","com.superme.common"})
public class ManagerContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerContentApplication.class, args);
    }

}
