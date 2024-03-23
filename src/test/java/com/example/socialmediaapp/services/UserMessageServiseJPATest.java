package com.example.socialmediaapp.services;

import com.example.socialmediaapp.dto.GroupDTO;
import com.example.socialmediaapp.dto.UserDTO;
import com.example.socialmediaapp.dto.UserMessageDTO;
import com.example.socialmediaapp.mappers.GroupMapper;
import com.example.socialmediaapp.mappers.UserMessageMapper;
import com.example.socialmediaapp.repositories.GroupRepository;
import com.example.socialmediaapp.repositories.UserMessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMessageServiseJPATest {
    @Autowired
    UserMessageServiseJPA userMessageServiseJPA;
    @Autowired
    UserMessageRepository userMessageRepository;
    @Autowired
    UserMessageMapper userMessageMapper;

    @Test
    void getUserMsgById() {
        Long userMsg = 1L;
        Optional<UserMessageDTO> result = userMessageServiseJPA.getUserMsgById(userMsg);
        assertTrue(result.isPresent());
        assertEquals(userMsg, result.get().getMessageId());
        assertEquals("Its me, hi", result.get().getMessageContent());
        assertEquals(2L, result.get().getSender_id().getId());
        assertEquals("anyamurm", result.get().getSenderName());
        assertEquals(1L, result.get().getReceiver_id().getId());
        assertEquals("mike_time", result.get().getReceiverName());
    }
    @Test
    void createUserMsg() {
        UserMessageDTO newUserMsg = new UserMessageDTO();
        newUserMsg.setMessageContent("newMessageContent");
        UserDTO newUser = new UserDTO();
        newUser.setId(10003L);
        newUser.setUsername("newUser1");
        newUser.setEmail("newuser1@example.com");
        newUser.setPassword("password1");
        UserDTO newUser2 = new UserDTO();
        newUser2.setId(10003L);
        newUser2.setUsername("newUser2");
        newUser2.setEmail("newuser2@example.com");
        newUser2.setPassword("password2");
        newUserMsg.setSenderName(newUser.getUsername());
        newUserMsg.setReceiverName(newUser.getUsername());
        UserMessageDTO createdUserMsg = userMessageServiseJPA.createUserMsg(newUserMsg);
        assertNotNull(createdUserMsg);
        assertNotNull(createdUserMsg.getMessageId());
        assertEquals("newMessageContent", createdUserMsg.getMessageContent());
        assertEquals("newUser1", createdUserMsg.getSenderName());
        assertEquals("newUser1", createdUserMsg.getReceiverName());
    }
    @Test
    void getAllUserMsg() {
        Iterable<UserMessageDTO> userMessageDTOS = userMessageServiseJPA.getAllUserMsg();
        assertNotNull(userMessageDTOS, "Returned Iterable<UserMessageDTO> is null");
    }
    @Test
    void deleteById_ForeignKeyViolation() {
        Long userMsg = 1L;
        assertThrows(DataIntegrityViolationException.class, () -> {
            userMessageServiseJPA.deleteMsgById(userMsg);
        }, "Deletion should fail due to foreign key constraint violation");
    }
    @Test
    void updateMsgById() {
        Long userMsg = 1L;
        UserMessageDTO updatedUserMsgData = new UserMessageDTO();
        updatedUserMsgData.setMessageContent("updatedMessage");
        Optional<UserMessageDTO> updatedUserMsg = userMessageServiseJPA.updateMsgById(userMsg, updatedUserMsgData);
        assertTrue(updatedUserMsg.isPresent());
        assertEquals(userMsg, updatedUserMsg.get().getMessageId());
        assertEquals("updatedMessage", updatedUserMsg.get().getMessageContent());
    }
}