package com.jonny.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.jonny")
@SpringBootApplication
public class WebApp {
	public static void main(String[] args) {
		SpringApplication.run(WebApp.class, args);
	}
}
