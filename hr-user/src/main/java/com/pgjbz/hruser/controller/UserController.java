package com.pgjbz.hruser.controller;

import com.pgjbz.hruser.model.User;
import com.pgjbz.hruser.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    @GetMapping(value = "/search")
    public ResponseEntity<User> findByEmail (@RequestParam(value = "email") String email, HttpServletRequest request) {
        log.info("Receiving request to find user by email {} from ip {}", email, request.getRemoteAddr());
        return ResponseEntity.ok(userService.findByEmail(email));
    }
    

}
