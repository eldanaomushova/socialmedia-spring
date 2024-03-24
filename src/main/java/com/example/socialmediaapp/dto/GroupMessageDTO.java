package com.example.socialmediaapp.dto;

import com.example.socialmediaapp.entities.Group;
import com.example.socialmediaapp.entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupMessageDTO {
    private Long id;
    @NotBlank(message = "Group id is required")
    private Group group;
    @NotBlank(message = "Group name is required")
    private String groupName;
    @NotBlank(message = "Sender id is required")
    private User sender_id;
    @NotBlank(message = "Sender name is required")
    private String senderName;
    private String messageContent;
}
