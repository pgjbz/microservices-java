package com.pgjbz.hroauth.dto;

import com.pgjbz.hroauth.model.User;
import lombok.Getter;
import org.springframework.lang.NonNull;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;

@Getter
public class UserResponseDTO {
    
    private final Long id;
    private final String name;
    private final String email;
    private final String password;
    private final Set<String> roles = new HashSet<>();

    public UserResponseDTO(@NonNull Long id,
                           @NonNull String name,
                           @NonNull String email,
                           @NonNull String password) {
        this.id = requireNonNull(id, "id is mandatory");
        this.name = requireNonNull(name, "id id mandatory");
        this.email = requireNonNull(email, "email is mandatory");
        this.password = requireNonNull(password, "password id mandatory");
    }

    public User toModel() {
        User user = new User(id, name, email, password);
        roles.forEach(user::addRole);
        return user;
    }

}
