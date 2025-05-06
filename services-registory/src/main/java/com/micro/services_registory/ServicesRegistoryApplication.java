package com.micro.services_registory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class  ServicesRegistoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicesRegistoryApplication.class, args);
	}

}
