package com.pgjbz.hruser.service;

import com.pgjbz.hruser.model.User;

public interface UserService {

    User findByEmail(String email);
    
}
