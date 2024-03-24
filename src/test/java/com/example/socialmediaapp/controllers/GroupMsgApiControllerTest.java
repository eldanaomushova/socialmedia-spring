package com.example.socialmediaapp.controllers;

import com.example.socialmediaapp.dto.GroupDTO;
import com.example.socialmediaapp.dto.GroupMessageDTO;
import com.example.socialmediaapp.entities.Group;
import com.example.socialmediaapp.entities.GroupMessage;
import com.example.socialmediaapp.entities.User;
import com.example.socialmediaapp.mappers.GroupMessageMapper;
import com.example.socialmediaapp.services.GroupMessageServise;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
public class GroupMsgApiControllerTest {

    @Autowired
    MockHttpSession session;
    @Autowired
    GroupMessageMapper groupMsgMapper;
    @Mock
    private GroupMessageServise groupMsgServise;
    @InjectMocks
    private GroupMsgApiController groupMsgApiController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getAllGroupMsg() throws Exception {
        when(groupMsgServise.getAllGroupMsg()).thenReturn(Collections.singletonList(new GroupMessageDTO()));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(groupMsgApiController).build();
        mockMvc.perform(get("/api/v1/groupmsg"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));

    }

    @Test
    public void getGroupMsg() throws Exception {
        when(groupMsgServise.getGroupMsgById(1L)).thenReturn(Optional.of(new GroupMessageDTO()));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(groupMsgApiController).build();

        mockMvc.perform(get("/api/v1/groupmsg/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void createGroupMsg() throws Exception{
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
        GroupMessageDTO groupMessageDTO = new GroupMessageDTO();
        groupMessageDTO.setId(3L);
        groupMessageDTO.setGroup(group);
        groupMessageDTO.setGroupName(group.getGroupName());
        groupMessageDTO.setSender_id(user);
        groupMessageDTO.setSenderName(user.getUsername());
        groupMessageDTO.setMessageContent("test messageContent");
        when(groupMsgServise.createGroupMsg(any(GroupMessageDTO.class))).thenReturn(groupMessageDTO);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(groupMsgApiController).build();
        mockMvc.perform(post("/api/v1/groupmsg")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupMessageDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.sender_id.id").value(1L))
                .andExpect(jsonPath("$.senderName").value("test username"))
                .andExpect(jsonPath("$.group.id").value(1L))
                .andExpect(jsonPath("$.groupName").value("test groupName"))
                .andExpect(jsonPath("$.messageContent").value("test messageContent"));
    }

    @Test
    public void updateGroupMsg() throws Exception{
        GroupMessageDTO updateGroupMsg = new GroupMessageDTO();
        updateGroupMsg.setMessageContent("testGroupMsg");
        when(groupMsgServise.updateGroupMsgById(1L, updateGroupMsg)).thenReturn(Optional.of(updateGroupMsg));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(groupMsgApiController).build();
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/groupmsg/{id}", 1L)
                        .content(objectMapper.writeValueAsString(updateGroupMsg))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messageContent").value("testGroupMsg"));
    }

    @Test
    public void deleteGroupMsg() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(groupMsgApiController).build();
        mockMvc.perform(delete("/api/v1/groupmsg/{id}", 2L))
                .andExpect(status().isNoContent());
    }

    @Test
    public void updateGroupMsg2() throws Exception{
        GroupMessageDTO updateGroupMsg = new GroupMessageDTO();
        updateGroupMsg.setMessageContent("testGroupMsg");
        when(groupMsgServise.updateGroupMsgById(1L, updateGroupMsg)).thenReturn(Optional.of(updateGroupMsg));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(groupMsgApiController).build();
        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/api/v1/groupmsg/{id}", 1L)
                        .content(objectMapper.writeValueAsString(updateGroupMsg))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messageContent").value("testGroupMsg"));
    }
}