package com.example.user1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class User1Application {

	public static void main(String[] args) {
		SpringApplication.run(User1Application.class, args);
	}

}
