package com.pgjbz.hruser.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static java.util.Objects.requireNonNull;

@Getter
@Entity
@Table(name = "TB_ROLE")
@NoArgsConstructor(onConstructor = @__(@Deprecated))
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @EqualsAndHashCode.Include
    @Column(nullable = false, length = 40)
    private String roleName;

    public Role(@NonNull String roleName) {
        this.roleName = requireNonNull(roleName, "role name is mandatory");
    }
    
}
