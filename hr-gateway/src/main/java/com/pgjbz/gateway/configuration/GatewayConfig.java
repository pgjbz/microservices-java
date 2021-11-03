package com.pgjbz.gateway.configuration;

import com.pgjbz.gateway.filter.SecurityFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig  {

    private final SecurityFilter securityFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return  builder.routes()
                .route("user", r -> r.path("/user/**")
                        .filters(f -> f.filter(securityFilter).rewritePath("/user/(.*)", "/$1"))
                        .uri("lb://user"))
                .route("payroll", r -> r.path("/payroll/**")
                        .filters(f -> f.filter(securityFilter).rewritePath("/payroll/(.*)", "/$1"))
                        .uri("lb://payroll"))
                .route("worker", r -> r.path("/worker/**")
                        .filters(f -> f.filter(securityFilter).rewritePath("/worker/(.*)", "/$1"))
                        .uri("lb://worker"))
                .build();
    }


}
