package com.pgjbz.hrpayroll.configuration;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreaker;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@Configuration
public class Resilience4JConfig {

	@Bean
	public Resilience4JCircuitBreaker circuitBreaker(Resilience4JCircuitBreakerFactory circuitBreakerFactory) {
		circuitBreakerFactory.configureDefault(s ->
				new Resilience4JConfigBuilder(s)
						.timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(5)).build())
						.build()
		);
		return circuitBreakerFactory.create("default");
	}

}
