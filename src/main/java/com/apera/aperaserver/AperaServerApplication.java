package com.apera.aperaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class AperaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AperaServerApplication.class, args);
	}
}

