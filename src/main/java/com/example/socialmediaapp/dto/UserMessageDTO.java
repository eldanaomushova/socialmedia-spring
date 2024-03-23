package com.example.socialmediaapp.dto;

import com.example.socialmediaapp.entities.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMessageDTO {
    private Long messageId;
    private User sender_id;
    private String senderName;
    private User receiver_id;
    private String receiverName;
    private String messageContent;
}
