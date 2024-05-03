package com.example.socialmediaapp.dto;

import com.example.socialmediaapp.entities.Group;
import com.example.socialmediaapp.entities.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    @NotNull(message = "Must be not null")
    @Email(message = "Invalid email format")
    private String email;
    private String password;
    private Set<String> roles;
    public UserDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.roles = user.getRoles().stream().map(userRole -> userRole.getRolename().name()).collect(Collectors.toSet());
    }
}
