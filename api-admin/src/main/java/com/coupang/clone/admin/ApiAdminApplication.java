package com.coupang.clone.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.coupang.clone")
@EntityScan(basePackages = "com.coupang.clone.domain")
@EnableJpaRepositories(basePackages = "com.coupang.clone.domain")
public class ApiAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiAdminApplication.class, args);
    }
}
