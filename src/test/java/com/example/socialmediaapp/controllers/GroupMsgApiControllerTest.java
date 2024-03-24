package com.example.socialmediaapp.controllers;

import com.example.socialmediaapp.dto.GroupMessageDTO;
import com.example.socialmediaapp.entities.Group;
import com.example.socialmediaapp.entities.GroupMessage;
import com.example.socialmediaapp.entities.User;
import com.example.socialmediaapp.mappers.GroupMessageMapper;
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
public class GroupMsgApiControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    @Autowired
    MockHttpSession session;
    @Autowired
    GroupMessageMapper groupMsgMapper;
    @Mock
    private GroupMessageServise groupMsgServise;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    public void getAllGroupMsg() throws Exception {
        mockMvc.perform(get("/api/v1/groupmsg"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].group").exists())
                .andExpect(jsonPath("$[0].groupName").exists())
                .andExpect(jsonPath("$[0].sender_id").exists())
                .andExpect(jsonPath("$[0].senderName").exists())
                .andExpect(jsonPath("$[0].messageContent").exists());
    }

    @Test
    public void getGroupMsg() throws Exception {
        mockMvc.perform(get("/api/v1/groupmsg/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.group.id").value(1L))
                .andExpect(jsonPath("$.groupName").value("students information"))
                .andExpect(jsonPath("$.sender_id.id").value(2L))
                .andExpect(jsonPath("$.senderName").value("anyamurm"))
                .andExpect(jsonPath("$.messageContent").value("hi everyone"));
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
        User user = new User(1L, "mike_time", "mike@gmail.com", "1425e");
        Group group = new Group(1L, "students information", user, user.getUsername());
        GroupMessage updateGroupMsg = new GroupMessage(1L, group, group.getGroupName(), user, user.getUsername(), "test updateGroupMessage");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/groupmsg/{id}", 1L)
                        .content(objectMapper.writeValueAsString(updateGroupMsg))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.group.id").value(1L))
                .andExpect(jsonPath("$.groupName").value("students information"))
                .andExpect(jsonPath("$.sender_id.id").value(2L))
                .andExpect(jsonPath("$.senderName").value("anyamurm"))
                .andExpect(jsonPath("$.messageContent").value("test updateGroupMessage"));
    }

    @Test
    public void deleteGroupMsg() throws Exception {
        doThrow(DataIntegrityViolationException.class).when(groupMsgServise).deleteGroupMsgById(2L);
        mockMvc.perform(delete("/api/v1/groupmsg/{id}", 2L))
                .andExpect(status().isNoContent());
    }

    @Test
    public void updateGroupMsg2() throws Exception{
        User user = new User(1L, "mike_time", "mike@gmail.com", "1425e");
        Group group = new Group(1L, "students information", user, user.getUsername());
        GroupMessage updateGroupMsg = new GroupMessage(1L, group, group.getGroupName(), user, user.getUsername(), "test updateGroupMessage");
        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/api/v1/groupmsg/{id}", 1L)
                        .content(objectMapper.writeValueAsString(updateGroupMsg))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.group.id").value(1L))
                .andExpect(jsonPath("$.groupName").value("students information"))
                .andExpect(jsonPath("$.sender_id.id").value(2L))
                .andExpect(jsonPath("$.senderName").value("anyamurm"))
                .andExpect(jsonPath("$.messageContent").value("test updateGroupMessage"));
    }
}