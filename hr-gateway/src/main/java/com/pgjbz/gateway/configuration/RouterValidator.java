package com.pgjbz.gateway.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class RouterValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/oauth/oauth/token"
    );

    public boolean isSecured(ServerHttpRequest request) {
        return openApiEndpoints.stream().anyMatch(uri -> request.getURI().getPath().contains(uri));
    }


}
