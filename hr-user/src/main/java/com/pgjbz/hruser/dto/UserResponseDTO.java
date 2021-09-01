package com.pgjbz.hruser.dto;

import org.springframework.lang.NonNull;

import lombok.Getter;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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

    public Set<String> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    public void addRole(String role) {
        roles.add(role);
    }
}
