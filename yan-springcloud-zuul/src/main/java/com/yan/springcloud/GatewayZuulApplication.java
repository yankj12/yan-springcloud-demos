package com.yan.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.yan.springcloud.filter.TokenFilter;

@EnableZuulProxy
@EnableDiscoveryClient    // EnableDiscoveryClient，是为了让 Zuul 能够访问 Eureka Server 并注册自身
@EnableCircuitBreaker     // EnableCircuitBreaker 则是提供能力，让 Zuul 的转发同时具备熔断能力。
@SpringBootApplication
public class GatewayZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayZuulApplication.class, args);
	}
	
	@Bean
	public TokenFilter tokenFilter() {
		return new TokenFilter();
	}
}