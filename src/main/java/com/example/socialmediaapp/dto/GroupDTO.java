package com.example.socialmediaapp.dto;

import com.example.socialmediaapp.entities.User;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {
    private Long id;
    @NotBlank(message = "Group name is required")
    private String groupName;
    private User creator;
    @NotNull(message = "Creator User Name must not be null")
    private String creatorUserName;
}

