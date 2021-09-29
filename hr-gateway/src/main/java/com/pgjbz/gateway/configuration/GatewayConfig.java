package com.pgjbz.gateway.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig  {

    private final SecurityConfig securityConfig;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return  builder.routes()
                .route("user", r -> r.path("/user/**")
                        .filters(f -> f.filter(securityConfig).rewritePath("/user/(.*)", "/$1"))
                        .uri("lb://user"))
                .route("payroll", r -> r.path("/payroll/**")
                        .filters(f -> f.filter(securityConfig).rewritePath("/payroll/(.*)", "/$1"))
                        .uri("lb://payroll"))
                .route("worker", r -> r.path("/worker/**")
                        .filters(f -> f.filter(securityConfig).rewritePath("/worker/(.*)", "/$1"))
                        .uri("lb://worker"))
                .build();
    }


}
