package com.lcwd.user.service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {
	@Bean
	@LoadBalanced //It allow to use microservice name instead of host:port 
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
