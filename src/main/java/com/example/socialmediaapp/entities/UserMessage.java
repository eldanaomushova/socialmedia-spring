package com.example.socialmediaapp.entities;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_messages")
public class UserMessage {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue
    private Long messageId;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender_id;

    private String senderName;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver_id;
    private String receiverName;

    private String messageContent;
}
