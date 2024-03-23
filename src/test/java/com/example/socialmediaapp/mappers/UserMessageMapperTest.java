package com.example.socialmediaapp.mappers;

import com.example.socialmediaapp.dto.UserDTO;
import com.example.socialmediaapp.dto.UserMessageDTO;
import com.example.socialmediaapp.entities.User;
import com.example.socialmediaapp.entities.UserMessage;
import org.checkerframework.checker.units.qual.A;
import org.hibernate.annotations.SoftDelete;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMessageMapperTest {
    @Autowired
    UserMessageMapper userMessageMapper;

    @Test
    void userMessageDTOtoUserMessage() {
        UserMessage userMessage = UserMessage.builder()
                .messageId(12L)
                .messageContent("Test Message Content")
                .build();
        User user1 = User.builder()
                .id(12L)
                .username("Test Username 1")
                .email("Test Email 1")
                .password("test password 1")
                .build();
        User user2 = User.builder()
                .id(12L)
                .username("Test Username 2")
                .email("Test Email 2")
                .password("test password 2")
                .build();
        userMessage.setSender_id(user1);
        userMessage.setReceiver_id(user2);
        userMessage.setSenderName(user1.getUsername());
        userMessage.setReceiverName(user2.getUsername());
        UserMessageDTO userMessageDTO = userMessageMapper.userMessagetoUserMessageDTO(userMessage);
        assertNotNull(userMessageDTO);
        assertEquals(12L, userMessageDTO.getSender_id().getId());
        assertEquals("Test Username 1", userMessageDTO.getSenderName());
        assertEquals(12L, userMessageDTO.getReceiver_id().getId());
        assertEquals("Test Username 2", userMessageDTO.getReceiverName());
        assertEquals("Test Message Content", userMessageDTO.getMessageContent());
    }

    @Test
    void userMessagetoUserMessageDTO() {
        UserMessageDTO userMessageDTO = UserMessageDTO.builder()
                .messageId(12L)
                .messageContent("Test Message Content")
                .build();
        UserDTO userdto1 = UserDTO.builder()
                .id(12L)
                .username("Test Username 1")
                .email("Test Email 1")
                .password("test password 1")
                .build();
        UserDTO userdto2 = UserDTO.builder()
                .id(12L)
                .username("Test Username 2")
                .email("Test Email 2")
                .password("test password 2")
                .build();
        User user1 = User.builder()
                .id(userdto1.getId())
                .build();
        User user2 = User.builder()
                .id(userdto2.getId())
                .build();
        userMessageDTO.setSender_id(user1);
        userMessageDTO.setReceiver_id(user2);
        userMessageDTO.setSenderName(userdto1.getUsername());
        userMessageDTO.setReceiverName(userdto2.getUsername());
        UserMessage userMessage = userMessageMapper.userMessageDTOtoUserMessage(userMessageDTO);
        assertNotNull(userMessageDTO);
        assertEquals(12L, userMessageDTO.getSender_id().getId());
        assertEquals("Test Username 1", userMessageDTO.getSenderName());
        assertEquals(12L, userMessageDTO.getReceiver_id().getId());
        assertEquals("Test Username 2", userMessageDTO.getReceiverName());
        assertEquals("Test Message Content", userMessageDTO.getMessageContent());
    }
}