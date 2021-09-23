package com.pgjbz.hroauth.model;

import lombok.Getter;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Getter
public class User implements UserDetails {
    
    private final Long id;
    private final String name;
    private final String email;
    private final String password;
    private final Set<String> roles = new HashSet<>();

    public User(@NonNull Long id,
                @NonNull String name,
                @NonNull String email, 
                @NonNull String password) {
        this.id = requireNonNull(id, "Id is mandatory");
        this.name = requireNonNull(name, "name is mandatory");
        this.email = requireNonNull(email, "email is mandatory");
        this.password = requireNonNull(password, "password is mandatory");
    }

    public Set<String> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    public void addRole(String role) {
        roles.add(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
