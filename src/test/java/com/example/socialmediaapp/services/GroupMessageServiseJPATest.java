package com.example.socialmediaapp.services;

import com.example.socialmediaapp.dto.GroupDTO;
import com.example.socialmediaapp.dto.GroupMessageDTO;
import com.example.socialmediaapp.dto.UserDTO;
import com.example.socialmediaapp.dto.UserMessageDTO;
import com.example.socialmediaapp.entities.GroupMessage;
import com.example.socialmediaapp.mappers.GroupMessageMapper;
import com.example.socialmediaapp.repositories.GroupMessageRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupMessageServiseJPATest {
    @Autowired
    GroupMessageServiseJPA groupMessageServiseJPA;
    @Autowired
    GroupMessageRepository groupMessageRepository;
    @Autowired
    GroupMessageMapper groupMessageMapper;

    @Test
    void getGroupMsgById() {
        Long groupMsg = 1L;
        Optional<GroupMessageDTO> result = groupMessageServiseJPA.getGroupMsgById(groupMsg);
        assertTrue(result.isPresent());
        assertEquals(groupMsg, result.get().getId());
        assertEquals(1L, result.get().getGroup().getId());
        assertEquals("students information", result.get().getGroupName());
        assertEquals("hi everyone", result.get().getMessageContent());
        assertEquals(2L, result.get().getSender_id().getId());
        assertEquals("anyamurm", result.get().getSenderName());
    }

    @Test
    void createGroupMsg() {
        GroupMessageDTO newGroupMsg = new GroupMessageDTO();
        newGroupMsg.setId(2L);
        newGroupMsg.setMessageContent("newMessageToGroup");
        UserDTO newUser = new UserDTO();
        newUser.setId(10003L);
        newUser.setUsername("newUser1");
        newUser.setEmail("newuser1@example.com");
        newUser.setPassword("password1");
        GroupDTO newGroup = new GroupDTO();
        newGroup.setId(1L);
        newGroup.setGroupName("newGroupName");
        newGroupMsg.setGroupName(newGroup.getGroupName());
        newGroupMsg.setSenderName(newUser.getUsername());
        newGroup.setCreatorUserName(newUser.getUsername());
        GroupMessageDTO createdGroupMsg = groupMessageServiseJPA.createGroupMsg(newGroupMsg);
        assertNotNull(createdGroupMsg);
        assertEquals("newMessageToGroup", createdGroupMsg.getMessageContent());
        assertEquals("newGroupName", createdGroupMsg.getGroupName());
        assertEquals("newUser1", createdGroupMsg.getSenderName());
    }

    @Test
    void getAllGroupMsg() {
        Iterable<GroupMessageDTO> groupMessageDTOS = groupMessageServiseJPA.getAllGroupMsg();
        assertNotNull(groupMessageDTOS, "Returned Iterable<GroupMessageDTO> is null");
    }

    @Test
    void deleteGroupMsgById() {
        Long groupMessageId = 1L;
        assertThrows(DataIntegrityViolationException.class, () -> {
            groupMessageServiseJPA.deleteGroupMsgById(groupMessageId);
        }, "Deletion should fail due to foreign key constraint violation");
    }

    @Test
    void updateGroupMsgNameById() {
        Long groupMessageId = 1L;
        GroupMessageDTO updatedGroupMessageData = new GroupMessageDTO();
        updatedGroupMessageData.setMessageContent("updatedGroupMessage");
        Optional<GroupMessageDTO> updatedGroupMessage = groupMessageServiseJPA.updateGroupMsgById(groupMessageId, updatedGroupMessageData);
        assertTrue(updatedGroupMessage.isPresent());
        assertEquals(groupMessageId, updatedGroupMessage.get().getId());
        assertEquals("updatedGroupMessage", updatedGroupMessage.get().getMessageContent());
    }
}