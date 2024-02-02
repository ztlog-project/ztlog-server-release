package com.devlog.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = {"com.devlog"})
public class ZtlogApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZtlogApiApplication.class, args);
	}

}
