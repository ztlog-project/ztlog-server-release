package com.devlog.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.devlog.core"})
public class ZtlogAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZtlogAdminApplication.class, args);
    }

}
