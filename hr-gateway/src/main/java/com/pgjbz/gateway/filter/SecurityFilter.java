package com.pgjbz.gateway.filter;

import com.pgjbz.gateway.configuration.Constants;
import com.pgjbz.gateway.configuration.RouterValidator;
import com.pgjbz.gateway.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

@Slf4j
@Component
@RefreshScope
@RequiredArgsConstructor
public class SecurityFilter implements GatewayFilter {

    private final RouterValidator routerValidator;
    private final JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Checking token...");
        ServerHttpRequest request = exchange.getRequest();
        if(routerValidator.isSecured(request)) {

            if(isAuthMissing(request))
                return onError(exchange);
            final String token = getAuthHeader(request);

            final String[] roles = rolesToArray(token);

            if(jwtUtil.isInvalid(token))
                return onError(exchange);

            if(routerValidator.onlyAdmin(request)
                    && !isAdmin(roles))
                return onError(exchange);

            if(routerValidator.onlyOperatorOrAdmin(request)
                && !(isOperator(roles) || isAdmin(roles)))
                return onError(exchange);
        }
        return chain.filter(exchange);
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0);
    }

    private Mono<Void> onError(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private String[] rolesToArray(String token) {
        return jwtUtil.getRoles(token).replaceAll("[\\[\\]\\s]", "").split(",");
    }

    private boolean isAdmin(String[] roles) {
        return Stream.of(roles).anyMatch(role ->
            role.equalsIgnoreCase(Constants.ROLE_ADMIN));
    }

    private boolean isOperator(String[] roles) {
        return Stream.of(roles).anyMatch(role -> role.equalsIgnoreCase(Constants.ROLE_OPERATOR));
    }

}
