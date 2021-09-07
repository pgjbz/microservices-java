package com.pgjbz.hroauth.service;

import com.pgjbz.hroauth.model.User;

public interface UserService {

    User findByEmail(String email);

}
