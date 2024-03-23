package com.example.socialmediaapp.dto;

import com.example.socialmediaapp.entities.Group;
import com.example.socialmediaapp.entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupMessageDTO {
    private Long id;
    private Group group;
    private String groupName;
    private User sender_id;
    private String senderName;
    private String messageContent;
}
