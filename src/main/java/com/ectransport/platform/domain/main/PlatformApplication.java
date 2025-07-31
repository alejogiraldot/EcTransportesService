package com.ectransport.platform.domain.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.ectransport.platform.infrastructure.entity")
@EnableJpaRepositories(basePackages = "com.ectransport.platform.infrastructure.repository")
@ComponentScan(basePackages = "com.ectransport.platform")
public class PlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatformApplication.class, args);
	}

}
