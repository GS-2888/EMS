package com.ems.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class APIGatewayApplication {

	public static void main(String [] args) {
		SpringApplication.run(APIGatewayApplication.class,args);
	}
	
	
}
