package com.example.socialmediaapp.controllers;

import com.example.socialmediaapp.dto.GroupMembersDTO;
import com.example.socialmediaapp.dto.GroupMessageDTO;
import com.example.socialmediaapp.entities.Group;
import com.example.socialmediaapp.entities.GroupMessage;
import com.example.socialmediaapp.entities.User;
import com.example.socialmediaapp.mappers.GroupMembersMapper;
import com.example.socialmediaapp.mappers.GroupMessageMapper;
import com.example.socialmediaapp.services.GroupMembersServise;
import com.example.socialmediaapp.services.GroupMessageServise;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupsMembersApiControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    @Autowired
    MockHttpSession session;
    @Autowired
    GroupMembersMapper groupMembMapper;
    @Mock
    private GroupMembersServise groupMembServise;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getAllGroupMemb() throws Exception {
        mockMvc.perform(get("/api/v1/groupmemb"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].groupId").exists())
                .andExpect(jsonPath("$[0].groupName").exists())
                .andExpect(jsonPath("$[0].userId").exists())
                .andExpect(jsonPath("$[0].userName").exists());
    }

    @Test
    public void getGroupMemb() throws Exception {
        mockMvc.perform(get("/api/v1/groupmemb/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.groupId.id").value(1L))
                .andExpect(jsonPath("$.groupName").value("students information"))
                .andExpect(jsonPath("$.userId.id").value(1L))
                .andExpect(jsonPath("$.userName").value("mike_time"));
    }

    @Test
    public void createGroupMemb() throws Exception {
        User user = new User();
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
        doThrow(DataIntegrityViolationException.class).when(groupMembServise).deleteGroupMembById(2L);
        mockMvc.perform(delete("/api/v1/groupmemb/{id}", 2L))
                .andExpect(status().isNoContent());
    }
}