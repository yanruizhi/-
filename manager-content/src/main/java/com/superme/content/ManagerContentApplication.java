package com.superme.content;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.archaius.ArchaiusAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.superme.content","com.superme.common"},exclude = {ArchaiusAutoConfiguration.class})
public class ManagerContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerContentApplication.class, args);
    }

}
