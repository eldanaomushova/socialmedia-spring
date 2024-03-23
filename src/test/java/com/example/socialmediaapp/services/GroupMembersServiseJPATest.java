package com.example.socialmediaapp.services;

import com.example.socialmediaapp.dto.GroupDTO;
import com.example.socialmediaapp.dto.GroupMembersDTO;
import com.example.socialmediaapp.dto.GroupMessageDTO;
import com.example.socialmediaapp.dto.UserDTO;
import com.example.socialmediaapp.mappers.GroupMembersMapper;
import com.example.socialmediaapp.repositories.GroupMembersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupMembersServiseJPATest {
    @Autowired
    GroupMembersServiseJPA groupMembersServiseJPA;
    @Autowired
    GroupMembersRepository groupMembersRepository;
    @Autowired
    GroupMembersMapper groupMembersMapper;

    @Test
    void getGroupMembById() {
        Long groupMemb = 1L;
        Optional<GroupMembersDTO> result = groupMembersServiseJPA.getGroupMembById(groupMemb);
        assertTrue(result.isPresent());
        assertEquals(groupMemb, result.get().getId());
        assertEquals(1L, result.get().getGroupId().getId());
        assertEquals("students information", result.get().getGroupName());
        assertEquals(1L, result.get().getUserId().getId());
        assertEquals("mike_time", result.get().getUserName());
    }

    @Test
    void createGroupMemb() {
        GroupMembersDTO newGroupMemb = new GroupMembersDTO();
        newGroupMemb.setId(1L);
        UserDTO newUser = new UserDTO();
        newUser.setId(10003L);
        newUser.setUsername("newUser1");
        newUser.setEmail("newuser1@example.com");
        newUser.setPassword("password1");
        GroupDTO newGroup = new GroupDTO();
        newGroup.setId(1L);
        newGroup.setGroupName("newGroupName");
        newGroup.setCreatorUserName(newUser.getUsername());
        newGroupMemb.setUserName(newUser.getUsername());
        newGroupMemb.setGroupName(newGroup.getGroupName());
        GroupMembersDTO createdGroupMemb = groupMembersServiseJPA.createGroupMemb(newGroupMemb);
        assertNotNull(createdGroupMemb);
        assertEquals("newGroupName", createdGroupMemb.getGroupName());
        assertEquals("newUser1", createdGroupMemb.getUserName());
    }

    @Test
    void getAllGroupMemb() {
        Iterable<GroupMembersDTO> groupMembersDTOS = groupMembersServiseJPA.getAllGroupMemb();
        assertNotNull(groupMembersDTOS, "Returned Iterable<GroupMessageDTO> is null");
    }

    @Test
    void deleteGroupMembById() {
        Long groupMemb = 1L;
        assertThrows(DataIntegrityViolationException.class, () -> {
            groupMembersServiseJPA.deleteGroupMembById(groupMemb);
        }, "Deletion should fail due to foreign key constraint violation");
    }
}