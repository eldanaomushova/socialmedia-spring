package com.example.socialmediaapp.entities;

import io.swagger.models.auth.In;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String username;
    @NotNull
    @NotBlank
    private String email;
    private String password;
    private Boolean locked = false;
    private Boolean enabled = false;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles = new HashSet<>();
    private String twofa_code;
    private String twofa_expire_time;
    private int userLimit;

    public User(String username,
                String email,
                String password,
                UserRole appUserRole) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = new HashSet<>();
        this.roles.add(appUserRole);
    }
}