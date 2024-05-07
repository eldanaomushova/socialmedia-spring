package com.example.socialmediaapp.controllers;

import com.example.socialmediaapp.dto.UserMessageDTO;
import com.example.socialmediaapp.entities.User;
import com.example.socialmediaapp.entities.UserRole;
import com.example.socialmediaapp.mappers.UserMessageMapper;
import com.example.socialmediaapp.services.UserMessageServise;
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
public class UserMessagesApiControllerTest {

    @Autowired
    MockHttpSession session;
    @Autowired
    UserMessageMapper userMsgMapper;
    @Mock
    private UserMessageServise userMsgServise;

    private ObjectMapper objectMapper = new ObjectMapper();
    @InjectMocks
    private UserMessagesApiController userMessagesApiController;


    @Test
    public void getAllUserMsg() throws Exception {
        when(userMsgServise.getAllUserMsg()).thenReturn(Collections.singletonList(new UserMessageDTO()));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userMessagesApiController).build();
        mockMvc.perform(get("/api/v1/usermsg"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
    }

    @Test
    public void getUserMsgById() throws Exception {
        when(userMsgServise.getUserMsgById(1L)).thenReturn(Optional.of(new UserMessageDTO()));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userMessagesApiController).build();

        mockMvc.perform(get("/api/v1/usermsg/{id}", 1L))
                .andExpect(status().isOk());
    }
    @Test
    public void createGroup() throws Exception {
        User user = new User(request.getFirstName(), request.getLastName(), request.getEmail(), UserRole.user);
        user.setId(1L);
        user.setUsername("test username");
        user.setEmail("test email");
        user.setPassword("test password");
        User user2 = new User(request.getFirstName(), request.getLastName(), request.getEmail(), UserRole.user);
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
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userMessagesApiController).build();
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
        UserMessageDTO updatedUserMsg = new UserMessageDTO();
        updatedUserMsg.setMessageContent("testUserMsg");
        when(userMsgServise.updateMsgById(1L, updatedUserMsg)).thenReturn(Optional.of(updatedUserMsg));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userMessagesApiController).build();
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/usermsg/{id}", 1L)
                        .content(objectMapper.writeValueAsString(updatedUserMsg))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messageContent").value("testUserMsg"));
    }

    @Test
    public void deleteUserMsg() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userMessagesApiController).build();
        mockMvc.perform(delete("/api/v1/usermsg/{id}", 2L))
                .andExpect(status().isNoContent());
    }
    @Test
    public void patchUserMsg2() throws Exception{
        UserMessageDTO updatedUserMsg = new UserMessageDTO();
        updatedUserMsg.setMessageContent("testUserMsg");
        when(userMsgServise.updateMsgById(1L, updatedUserMsg)).thenReturn(Optional.of(updatedUserMsg));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userMessagesApiController).build();
        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/api/v1/usermsg/{id}", 1L)
                        .content(objectMapper.writeValueAsString(updatedUserMsg))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messageContent").value("testUserMsg"));
    }
}