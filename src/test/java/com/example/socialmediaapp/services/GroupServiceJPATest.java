package com.example.socialmediaapp.services;

import com.example.socialmediaapp.dto.GroupDTO;
import com.example.socialmediaapp.dto.UserDTO;
import com.example.socialmediaapp.mappers.GroupMapper;
import com.example.socialmediaapp.repositories.GroupRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupServiceJPATest {
    @Autowired
    GroupServiceJPA groupServiceJPA;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    GroupMapper groupMapper;

    @Test
    void getGroupById() {
        Long groupId = 1L;
        Optional<GroupDTO> result = groupServiceJPA.getGroupById(groupId);
        assertTrue(result.isPresent());
        assertEquals(groupId, result.get().getId());
        assertEquals("students information", result.get().getGroupName());
        assertEquals(1, result.get().getCreator().getId());
        assertEquals("mike_time", result.get().getCreatorUserName());
    }

    @Test
    void createGroup() {
        GroupDTO newGroup = new GroupDTO();
        newGroup.setGroupName("newGroup");
        UserDTO newUser = new UserDTO();
        newUser.setId(10003L);
        newUser.setUsername("newUser");
        newUser.setEmail("newuser@example.com");
        newUser.setPassword("password");
        newGroup.setCreatorUserName(newUser.getUsername());

        GroupDTO createdGroup = groupServiceJPA.createGroup(newGroup);
        assertNotNull(createdGroup);
        assertNotNull(createdGroup.getId());
        assertEquals("newGroup", createdGroup.getGroupName());
        assertEquals("newUser", createdGroup.getCreatorUserName());
    }

    @Test
    void getAllGroupsOfUser() {
        Iterable<GroupDTO> groupDTOS = groupServiceJPA.getAllGroupsOfUser();
        assertNotNull(groupDTOS, "Returned Iterable<GroupDTO> is null");
    }

    @Test
    void deleteById_ForeignKeyViolation() {
        Long groupId = 1L;
        assertThrows(DataIntegrityViolationException.class, () -> {
            groupServiceJPA.deleteGroupById(groupId);
        }, "Deletion should fail due to foreign key constraint violation");
    }

    @Test
    void updateGroupNameById() {
        Long groupId = 1L;
        GroupDTO updatedGroupData = new GroupDTO();
        updatedGroupData.setGroupName("updatedGroupName");
        Optional<GroupDTO> updatedGroup = groupServiceJPA.updateGroupNameById(groupId, updatedGroupData);
        assertTrue(updatedGroup.isPresent());
        assertEquals(groupId, updatedGroup.get().getId());
        assertEquals("updatedGroupName", updatedGroup.get().getGroupName());
    }
}