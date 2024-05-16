package com.example.socialmediaapp.dto;

import com.example.socialmediaapp.entities.User;
import com.example.socialmediaapp.entities.UserRole;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import java.util.Set;
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
    private Set<UserRole> roles;
    private Boolean locked = false;
    private Boolean enabled = false;
    private String twofa_code;
    private String twofa_expire_time;
    private int limit;
    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }

}