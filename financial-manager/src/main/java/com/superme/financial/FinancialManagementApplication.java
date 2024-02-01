package com.superme.financial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.archaius.ArchaiusAutoConfiguration;

@SpringBootApplication(exclude = {ArchaiusAutoConfiguration.class})
public class FinancialManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinancialManagementApplication.class, args);
    }

}
