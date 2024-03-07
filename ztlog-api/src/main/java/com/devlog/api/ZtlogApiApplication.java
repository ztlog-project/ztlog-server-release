package com.devlog.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.devlog.core"})
public class ZtlogApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZtlogApiApplication.class, args);
	}

}
