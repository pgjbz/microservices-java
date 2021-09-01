package com.pgjbz.hruser.model;

import java.util.Set;
import java.util.HashSet;

import org.springframework.lang.NonNull;

import lombok.Getter;
import lombok.NoArgsConstructor;

import static java.util.Objects.requireNonNull;

import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.pgjbz.hruser.dto.UserResponseDTO;

@Getter
@Entity
@Table(name = "TB_USER")
@NoArgsConstructor(onConstructor = @__(@Deprecated))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 70)
    private String name;
    @Column(nullable = false, length = 100)
    private String email;
    @Lob
    @Column(nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TB_USER_ROLE",
        joinColumns = {
            @JoinColumn(name = "USER_ID", nullable = false)
        },
        inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID", nullable = false)
        }
    )
    private final Set<Role> roles = new HashSet<>();

    public User(@NonNull String name, 
                @NonNull String email, 
                @NonNull String password) {
        this.name = requireNonNull(name, "name is mandatory");
        this.email = requireNonNull(email, "email is mandatory");
        this.password = requireNonNull(password, "password is mandatory");
    }

    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public UserResponseDTO toDto() {
        UserResponseDTO userResponseDTO = new UserResponseDTO(this.id, this.name, this.email, this.password);
        roles.forEach(role -> userResponseDTO.addRole(role.getRoleName()));
        return userResponseDTO;
    }
    
}
