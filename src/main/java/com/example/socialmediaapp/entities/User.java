package com.example.socialmediaapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue
    private Long id;
    private String username;
    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String email;
    private String password;

    @OneToMany(mappedBy = "username", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Group> groupSet;
}
