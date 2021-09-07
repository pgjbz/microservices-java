package com.pgjbz.hroauth.controller;

import com.pgjbz.hroauth.exception.RequestTimeOutException;
import com.pgjbz.hroauth.model.User;
import com.pgjbz.hroauth.service.UserService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreaker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping(value = "/oauth")
@RequiredArgsConstructor
public class OAuthController {

    private final UserService userService;
    private final Resilience4JCircuitBreaker circuitBreaker;

    @GetMapping(value = "/search")
    public ResponseEntity<User> findByEmail(@RequestParam(value = "email") String email,
                                           HttpServletRequest request){
        log.info("Receiving request [user email {} - from ip {}]", email, request.getRemoteAddr());
        User user = circuitBreaker.run(() -> userService.findByEmail(email),
                this::findByEmailAlternative);
        return ResponseEntity.ok(user);
    }

    private User findByEmailAlternative(Throwable throwable) {
        if(throwable instanceof FeignException.NotFound)
            throw (RuntimeException)throwable;
        log.error("Error on perform user find by email request, using alternative method", throwable);
        throw new RequestTimeOutException("Worker service is Unavailable");
    }

}
