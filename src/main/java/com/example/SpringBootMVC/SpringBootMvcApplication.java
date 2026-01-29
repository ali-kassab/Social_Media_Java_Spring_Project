package com.example.SpringBootMVC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringBootMvcApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMvcApplication.class, args);
	}
}
