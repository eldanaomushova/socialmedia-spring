package com.example.socialmediaapp.dto;

import com.example.socialmediaapp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMessageDTO {
    private Long messageId;
    private User sender_id;
    private User receiver_id;
    private String messageContent;
}
