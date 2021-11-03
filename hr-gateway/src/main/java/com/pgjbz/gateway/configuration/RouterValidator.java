package com.pgjbz.gateway.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

import static com.pgjbz.gateway.utils.ContainsUtil.contains;

@Slf4j
@Component
public class RouterValidator {

    public boolean isSecured(ServerHttpRequest request) {
        return Constants.OPEN_ENDPOINTS.stream().noneMatch(uri -> request.getURI().getPath().contains(uri));
    }

    public boolean onlyAdmin(ServerHttpRequest request) {
        return Constants.ADMIN.stream().anyMatch(uri -> request.getURI().getPath().contains(uri))
                || contains(request.getMethod(), Stream
                    .of(HttpMethod.values())
                    .filter(http -> !(http == HttpMethod.GET)).toArray(HttpMethod[]::new));
    }

    public boolean onlyOperatorOrAdmin(ServerHttpRequest request) {
        return Constants.OPERATOR.stream().anyMatch(uri -> request.getURI().getPath().contains(uri));
    }


}
