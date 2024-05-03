package com.example.socialmediaapp.dto;

import com.example.socialmediaapp.entities.Group;
import com.example.socialmediaapp.entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupMembersDTO {
    private Long id;
    private Group groupId;
    private String groupName;
    private User userId;
    private String userName;
}
