package com.example.userservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({
		"com.example.userservices",
		"com.example.commonservices"
})
public class UserservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserservicesApplication.class, args);
	}

}
