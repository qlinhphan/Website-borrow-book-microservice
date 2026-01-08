package com.example.bookservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BookservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookservicesApplication.class, args);
	}

}
