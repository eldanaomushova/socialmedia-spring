package com.example.socialmediaapp.mappers;

import com.example.socialmediaapp.dto.GroupDTO;
import com.example.socialmediaapp.dto.GroupMessageDTO;
import com.example.socialmediaapp.dto.UserDTO;
import com.example.socialmediaapp.entities.Group;
import com.example.socialmediaapp.entities.GroupMessage;
import com.example.socialmediaapp.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupMessageMapperTest {
    @Autowired
    GroupMessageMapper groupMessageMapper;

    @Test
    void groupMessageDTOtoGroupMessage() {
        GroupMessage groupMessage = GroupMessage.builder()
                .messageContent("Test message in group")
                .build();
        Group group = Group.builder()
                .id(1L)
                .groupName("Test GroupName")
                .build();
        User user = User.builder()
                .id(12L)
                .username("Test Username")
                .email("Test Email")
                .password("test password")
                .build();
        groupMessage.setMessageContent(groupMessage.getMessageContent());
        groupMessage.setGroup(group);
        groupMessage.setGroupName(group.getGroupName());
        groupMessage.setSender_id(user);
        groupMessage.setSenderName(user.getUsername());
        GroupMessageDTO groupMessageDTO = groupMessageMapper.groupMessagetoGroupMessageDTO(groupMessage);
        assertNotNull(groupMessageDTO);
        assertEquals(1L, groupMessageDTO.getGroup().getId());
        assertEquals("Test GroupName", groupMessageDTO.getGroupName());
        assertEquals(12L, groupMessageDTO.getSender_id().getId());
        assertEquals("Test Username", groupMessageDTO.getSenderName());
        assertEquals("Test message in group", groupMessageDTO.getMessageContent());
    }

    @Test
    void groupMessagetoGroupMessageDTO() {
        GroupMessageDTO groupMessageDTO = GroupMessageDTO.builder()
                .messageContent("Test message in group")
                .build();
        GroupDTO groupDTO = GroupDTO.builder()
                .id(1L)
                .groupName("Test GroupName")
                .build();
        UserDTO userDTO = UserDTO.builder()
                .id(12L)
                .username("Test Username")
                .email("Test Email")
                .password("test password")
                .build();

        Group group = Group.builder()
                .id(groupDTO.getId())
                .build();
        User user = User.builder()
                .id(userDTO.getId())
                .build();

        groupMessageDTO.setMessageContent(groupMessageDTO.getMessageContent());
        groupMessageDTO.setGroup(group);
        groupMessageDTO.setGroupName(groupDTO.getGroupName());
        groupMessageDTO.setSender_id(user);
        groupMessageDTO.setSenderName(userDTO.getUsername());
        GroupMessage groupMessage = groupMessageMapper.groupMessageDTOtoGroupMessage(groupMessageDTO);

        assertNotNull(groupMessage);
        assertEquals(1L, groupMessage.getGroup().getId());
        assertEquals("Test GroupName", groupMessage.getGroupName());
        assertEquals(12L, groupMessage.getSender_id().getId());
        assertEquals("Test Username", groupMessage.getSenderName());
        assertEquals("Test message in group", groupMessage.getMessageContent());
    }
}