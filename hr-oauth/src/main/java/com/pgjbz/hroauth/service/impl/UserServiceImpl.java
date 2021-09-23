package com.pgjbz.hroauth.service.impl;

import com.pgjbz.hroauth.dto.UserResponseDTO;
import com.pgjbz.hroauth.feignclient.UserFeignClient;
import com.pgjbz.hroauth.model.User;
import com.pgjbz.hroauth.service.UserService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserFeignClient userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByEmail(username);
        if(nonNull(user))
            return user;
        throw new UsernameNotFoundException(String.format("User with username %s not founded", user));
    }

    @Override
    public User findByEmail(String email) {
        log.info("Searching user by email {}", email);
        try {
            ResponseEntity<UserResponseDTO> userResponse = userFeignClient.findByEmail(email);
            UserResponseDTO userResponseBody = userResponse.getBody();
            assert userResponseBody != null;
            log.info("User by email {} founded", email);
            return userResponseBody.toModel();
        } catch (FeignException.NotFound e) {
            log.warn("User with email {} not founded", email);
            throw e;
        } catch (Exception e) {
            log.error("Error on search user", e);
            throw e;
        }
    }
}
