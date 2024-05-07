package com.example.socialmediaapp.controllers;

import com.example.socialmediaapp.dto.GroupMembersDTO;
import com.example.socialmediaapp.entities.*;
import com.example.socialmediaapp.mappers.GroupMembersMapper;
import com.example.socialmediaapp.services.GroupMembersServise;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class GroupsMembersApiControllerTest {

    @Autowired
    MockHttpSession session;
    @Autowired
    GroupMembersMapper groupMembMapper;
    @Mock
    private GroupMembersServise groupMembServise;

    private ObjectMapper objectMapper = new ObjectMapper();
    @InjectMocks
    private GroupsMembersApiController groupsMembersApiController;

    @Test
    public void getAllGroupMemb() throws Exception {
        when(groupMembServise.getAllGroupMemb()).thenReturn(Collections.singletonList(new GroupMembersDTO()));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(groupsMembersApiController).build();
        mockMvc.perform(get("/api/v1/groupmemb"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
    }

    @Test
    public void getGroupMemb() throws Exception {
        when(groupMembServise.getGroupMembById(1L)).thenReturn(Optional.of(new GroupMembersDTO()));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(groupsMembersApiController).build();
        mockMvc.perform(get("/api/v1/groupmemb/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void createGroupMemb() throws Exception {
        User user = new User(request.getFirstName(), request.getLastName(), request.getEmail(), UserRole.user);
        user.setId(1L);
        user.setUsername("test username");
        user.setEmail("test email");
        user.setPassword("test password");
        Group group = new Group();
        group.setId(1L);
        group.setGroupName("test groupName");
        group.setCreator(user);
        group.setCreatorUserName(user.getUsername());

        GroupMembersDTO groupMembersDTO = new GroupMembersDTO();
        groupMembersDTO.setId(3L);
        groupMembersDTO.setGroupId(group);
        groupMembersDTO.setGroupName(group.getGroupName());
        groupMembersDTO.setUserId(user);
        groupMembersDTO.setUserName(user.getUsername());
        when(groupMembServise.createGroupMemb(any(GroupMembersDTO.class))).thenReturn(groupMembersDTO);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(groupsMembersApiController).build();
        mockMvc.perform(post("/api/v1/groupmemb")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupMembersDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.groupId.id").value(1L))
                .andExpect(jsonPath("$.groupName").value("test groupName"))
                .andExpect(jsonPath("$.userId.id").value(1L))
                .andExpect(jsonPath("$.userName").value("test username"));
    }

    @Test
    public void deleteGroupMsg() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(groupsMembersApiController).build();
        mockMvc.perform(delete("/api/v1/groupmemb/{id}", 2L))
                .andExpect(status().isNoContent());
    }
}