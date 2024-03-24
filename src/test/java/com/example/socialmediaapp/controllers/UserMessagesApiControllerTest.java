package com.example.socialmediaapp.controllers;

import com.example.socialmediaapp.dto.UserMessageDTO;
import com.example.socialmediaapp.entities.User;
import com.example.socialmediaapp.entities.UserMessage;
import com.example.socialmediaapp.mappers.UserMessageMapper;
import com.example.socialmediaapp.services.UserMessageServise;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
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
public class UserMessagesApiControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    @Autowired
    MockHttpSession session;
    @Autowired
    UserMessageMapper userMsgMapper;
    @Mock
    private UserMessageServise userMsgServise;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getAllUserMsg() throws Exception {
        mockMvc.perform(get("/api/v1/usermsg"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].messageId").exists())
                .andExpect(jsonPath("$[0].sender_id").exists())
                .andExpect(jsonPath("$[0].senderName").exists())
                .andExpect(jsonPath("$[0].receiver_id").exists())
                .andExpect(jsonPath("$[0].receiverName").exists())
                .andExpect(jsonPath("$[0].messageContent").exists());
    }

    @Test
    public void getUserMsgById() throws Exception {
        mockMvc.perform(get("/api/v1/usermsg/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sender_id.id").value(2L))
                .andExpect(jsonPath("$.senderName").value("anyamurm"))
                .andExpect(jsonPath("$.receiver_id.id").value(1L))
                .andExpect(jsonPath("$.receiverName").value("mike_time"))
                .andExpect(jsonPath("$.messageContent").value("test updateUserMessage"));
    }
    @Test
    public void createGroup() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("test username");
        user.setEmail("test email");
        user.setPassword("test password");
        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("test username2");
        user2.setEmail("test email2");
        user2.setPassword("test password2");
        UserMessageDTO userMessageDTO = new UserMessageDTO();
        userMessageDTO.setMessageId(3L);
        userMessageDTO.setSender_id(user);
        userMessageDTO.setSenderName(user.getUsername());
        userMessageDTO.setReceiver_id(user2);
        userMessageDTO.setReceiverName(user2.getUsername());
        userMessageDTO.setMessageContent("test messageContent");
        when(userMsgServise.createUserMsg(any(UserMessageDTO.class))).thenReturn(userMessageDTO);
        mockMvc.perform(post("/api/v1/usermsg")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userMessageDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.messageId").value(3L))
                .andExpect(jsonPath("$.sender_id.id").value(1L))
                .andExpect(jsonPath("$.senderName").value("test username"))
                .andExpect(jsonPath("$.receiver_id.id").value(2L))
                .andExpect(jsonPath("$.receiverName").value("test username2"))
                .andExpect(jsonPath("$.messageContent").value("test messageContent"));
    }

    @Test
    public void updateUserMsg() throws Exception{
        User user = new User(1L, "mike_time", "mike@gmail.com", "1425e");
        User user2 = new User(2L, "anyamurm", "anya@gmail.com", "5134c");
        UserMessage updatedUserMsg = new UserMessage(1L, user, user.getUsername(),user2, user2.getUsername(), "test updateUserMessage");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/usermsg/{id}", 1L)
                        .content(objectMapper.writeValueAsString(updatedUserMsg))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messageId").value(1L))
                .andExpect(jsonPath("$.sender_id.id").value(2L))
                .andExpect(jsonPath("$.senderName").value("anyamurm"))
                .andExpect(jsonPath("$.receiver_id.id").value(1L))
                .andExpect(jsonPath("$.receiverName").value("mike_time"))
                .andExpect(jsonPath("$.messageContent").value("test updateUserMessage"));
    }

    @Test
    public void deleteUserMsg() throws Exception {
        doThrow(DataIntegrityViolationException.class).when(userMsgServise).deleteMsgById(2L);
        mockMvc.perform(delete("/api/v1/usermsg/{id}", 2L))
                .andExpect(status().isNoContent());
    }
    @Test
    public void patchUserMsg2() throws Exception{
        User user = new User(1L, "mike_time", "mike@gmail.com", "1425e");
        User user2 = new User(2L, "anyamurm", "anya@gmail.com", "5134c");
        UserMessage updatedUserMsg = new UserMessage(1L, user, user.getUsername(),user2, user2.getUsername(), "test updateUserMessage");
        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/api/v1/usermsg/{id}", 1L)
                        .content(objectMapper.writeValueAsString(updatedUserMsg))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messageId").value(1L))
                .andExpect(jsonPath("$.sender_id.id").value(2L))
                .andExpect(jsonPath("$.senderName").value("anyamurm"))
                .andExpect(jsonPath("$.receiver_id.id").value(1L))
                .andExpect(jsonPath("$.receiverName").value("mike_time"))
                .andExpect(jsonPath("$.messageContent").value("test updateUserMessage"));
    }
}