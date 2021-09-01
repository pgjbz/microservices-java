package com.pgjbz.hruser.service.impl;

import com.pgjbz.hruser.model.User;
import com.pgjbz.hruser.repository.UserRepository;
import com.pgjbz.hruser.service.UserService;

import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    
    @Override
    public User findByEmail(String email) {
        log.info("Searching user with email {}", email);
        User user = userRepository.findByEmail(email)
        .orElseThrow(() -> {
            String message = String.format("No user founded with email %s", email);
            log.warn(message);
            return new NoResultException(message);
        });
        return user;
    }
    
}
