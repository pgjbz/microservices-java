package com.pgjbz.hroauth.service;

import com.pgjbz.hroauth.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

}
