package com.example.socialmediaapp.controllers;

import com.example.socialmediaapp.dto.GroupDTO;
import com.example.socialmediaapp.entities.User;
import com.example.socialmediaapp.entities.UserRole;
import com.example.socialmediaapp.mappers.GroupMapper;
import com.example.socialmediaapp.services.GroupServise;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
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
public class GroupApiControllerTest {

    @Autowired
    MockHttpSession session;
    @Autowired
    GroupMapper groupMapper;
    @Mock
    private GroupServise groupServise;

    private ObjectMapper objectMapper = new ObjectMapper();
    @InjectMocks
    private GroupApiController groupApiController;


    @Test
    public void getAllGroups() throws Exception {
        when(groupServise.getAllGroupsOfUser()).thenReturn(Collections.singletonList(new GroupDTO()));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(groupApiController).build();
        mockMvc.perform(get("/api/v1/groups"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
    }
    @Test
    public void getGroup() throws Exception {
        when(groupServise.getGroupById(1L)).thenReturn(Optional.of(new GroupDTO()));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(groupApiController).build();

        mockMvc.perform(get("/api/v1/groups/{id}", 1L))
                .andExpect(status().isOk());
    }
    @Test
    public void createGroup() throws Exception {
        User user = new User(request.getFirstName(), request.getLastName(), request.getEmail(), UserRole.user);
        user.setId(1L);
        user.setUsername("test username");
        user.setEmail("test email");
        user.setPassword("test password");
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(3L);
        groupDTO.setCreator(user);
        groupDTO.setGroupName("test groupName");
        groupDTO.setCreatorUserName("test creatorUsername");
        when(groupServise.createGroup(any(GroupDTO.class))).thenReturn(groupDTO);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(groupApiController).build();
        mockMvc.perform(post("/api/v1/groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.creator.id").value(1L))
                .andExpect(jsonPath("$.groupName").value("test groupName"))
                .andExpect(jsonPath("$.creatorUserName").value("test creatorUsername"));
    }
    @Test
    public void updateGroup() throws Exception {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setGroupName("test groupName");
        when(groupServise.updateGroupNameById(1L, groupDTO)).thenReturn(Optional.of(groupDTO));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(groupApiController).build();
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/groups/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.groupName").value("test groupName"));
    }
    @Test
    public void deleteById() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(groupApiController).build();
        mockMvc.perform(delete("/api/v1/groups/{id}", 2L))
                .andExpect(status().isNoContent());
    }
    @Test
    public void updateGroup2() throws Exception {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setGroupName("test groupName");
        when(groupServise.updateGroupNameById(1L, groupDTO)).thenReturn(Optional.of(groupDTO));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(groupApiController).build();
        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/api/v1/groups/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.groupName").value("test groupName"));
    }
}