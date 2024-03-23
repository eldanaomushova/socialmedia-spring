package com.example.socialmediaapp.dto;

import com.example.socialmediaapp.entities.User;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {
    private Long id;
    private String groupName;
    private User creator;
    private String creatorUserName;
}

