package com.example.socialmediaapp.mappers;

import com.example.socialmediaapp.dto.GroupDTO;
import com.example.socialmediaapp.dto.GroupMembersDTO;
import com.example.socialmediaapp.dto.UserDTO;
import com.example.socialmediaapp.entities.Group;
import com.example.socialmediaapp.entities.GroupMembers;
import com.example.socialmediaapp.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupMembersMapperTest {
    @Autowired
    GroupMembersMapper groupMembersMapper;
    @Test
    void groupMembersDTOToGroupMembers() {
        GroupMembers groupMembers = GroupMembers.builder()
                .id(1L)
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
        groupMembers.setGroupId(group);
        groupMembers.setGroupName(group.getGroupName());
        groupMembers.setUserId(user);
        groupMembers.setUserName(user.getUsername());
        GroupMembersDTO groupMembersDTO = groupMembersMapper.groupMembersToGroupMembersDTO(groupMembers);
        assertNotNull(groupMembersDTO);
        assertEquals(1L, groupMembersDTO.getId());
        assertEquals(1L, groupMembersDTO.getGroupId().getId());
        assertEquals("Test GroupName", groupMembersDTO.getGroupName());
        assertEquals(12L, groupMembersDTO.getUserId().getId());
        assertEquals("Test Username", groupMembersDTO.getUserName());
    }
    @Test
    void groupMembersToGroupMembersDTO() {
        GroupMembersDTO groupMembersDTO = GroupMembersDTO.builder()
                .id(1L)
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
        groupMembersDTO.setGroupId(group);
        groupMembersDTO.setGroupName(groupDTO.getGroupName());
        groupMembersDTO.setUserId(user);
        groupMembersDTO.setUserName(userDTO.getUsername());
        GroupMembers groupMembers = groupMembersMapper.groupMembersDTOToGroupMembers(groupMembersDTO);
        assertNotNull(groupMembers);
        assertEquals(1L, groupMembers.getId());
        assertEquals(1L, groupMembers.getGroupId().getId());
        assertEquals("Test GroupName", groupMembers.getGroupName());
        assertEquals(12L, groupMembers.getUserId().getId());
        assertEquals("Test Username", groupMembers.getUserName());
    }
}