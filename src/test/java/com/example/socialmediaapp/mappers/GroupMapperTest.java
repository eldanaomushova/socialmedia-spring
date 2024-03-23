package com.example.socialmediaapp.mappers;

import com.example.socialmediaapp.dto.GroupDTO;
import com.example.socialmediaapp.dto.UserDTO;
import com.example.socialmediaapp.entities.Group;
import com.example.socialmediaapp.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupMapperTest {
    @Autowired
    GroupMapper groupMapper;

    @Test
    void groupToGroupDTO() {
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
        group.setCreator(user);
        group.setCreatorUserName(user.getUsername());
        GroupDTO groupDTO = groupMapper.groupToGroupDTO(group);
        assertNotNull(groupDTO);
        assertEquals("Test GroupName", groupDTO.getGroupName());
        assertEquals(12L, groupDTO.getCreator().getId());
        assertEquals("Test Username", groupDTO.getCreatorUserName());
    }

    @Test
    void groupDTOToGroup() {
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
        User user = User.builder()
                .id(userDTO.getId())
                .build();
        groupDTO.setCreator(user);
        groupDTO.setCreatorUserName(userDTO.getUsername());
        Group group = groupMapper.groupDTOToGroup(groupDTO);
        assertNotNull(group);
        assertEquals("Test GroupName", group.getGroupName());
        assertEquals(12L, group.getCreator().getId());
        assertEquals("Test Username", group.getCreatorUserName());
    }
}