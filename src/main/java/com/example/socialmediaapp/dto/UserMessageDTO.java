package com.example.socialmediaapp.dto;

import com.example.socialmediaapp.entities.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMessageDTO {
    private Long messageId;
    @NotBlank(message = "sender id is required")
    private User sender_id;
    @NotBlank(message = "sender name is required")
    private String senderName;
    @NotBlank(message = "receiver id is required")
    private User receiver_id;
    @NotBlank(message = "receiver name is required")
    private String receiverName;
    private String messageContent;
}
