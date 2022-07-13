package com.practice.carselling.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.practice.carselling.model.UserRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = "username",
                        name = "uk_username"
                )
        }
)
public class User implements UserDetails {

    private static final String NOT_EMPTY_MSG = " cannot be empty or null";

    @Id
    @SequenceGenerator(
            name = "users_sequence",
            sequenceName = "users_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "users_sequence"
    )
    private Long id;
    @Column(nullable = false)
    @NotEmpty(message = "Username" + NOT_EMPTY_MSG)
    private String username;
    @Column(nullable = false)
    @NotEmpty(message = "Password" + NOT_EMPTY_MSG)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Invalid UserRole value")
    private UserRole userRole;
    private String email;
    private String firstName;
    private String lastName;
    @Column(nullable = false)
    @NotNull(message = "Enabled flag" + NOT_EMPTY_MSG)
    private Boolean enabled;

    public User(String username, String password, UserRole userRole, String email, String firstName, String lastName, Boolean enabled) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return enabled;
    }
}
