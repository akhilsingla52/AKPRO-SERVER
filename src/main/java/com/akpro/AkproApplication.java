package com.akpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.akpro")
@SpringBootApplication
public class AkproApplication {

	public static void main(String[] args) {
		SpringApplication.run(AkproApplication.class, args);
	}
}
