package com.example.socialmediaapp.integrationTest;

import com.example.socialmediaapp.dto.GroupDTO;
import com.example.socialmediaapp.dto.GroupMembersDTO;
import com.example.socialmediaapp.dto.GroupMessageDTO;
import com.example.socialmediaapp.dto.UserMessageDTO;
import com.example.socialmediaapp.entities.Group;
import com.example.socialmediaapp.entities.User;
import com.example.socialmediaapp.entities.UserRole;
import com.example.socialmediaapp.services.GroupMembersServise;
import com.example.socialmediaapp.services.GroupMessageServise;
import com.example.socialmediaapp.services.GroupServise;
import com.example.socialmediaapp.services.UserMessageServise;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class IntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private GroupServise groupServise;
    @Mock
    private UserMessageServise userMessageServise;
    @Mock
    private GroupMessageServise groupMessageServise;
    @Mock
    private GroupMembersServise groupMembersServise;
    @Test
    public void testIntgrationsOfGroupAndUser() throws Exception {
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
        mockMvc.perform(post("/api/v1/groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.creator.id").value(1L))
                .andExpect(jsonPath("$.groupName").value("test groupName"))
                .andExpect(jsonPath("$.creatorUserName").value("test creatorUsername"));
        mockMvc.perform(get("/api/v1/groups/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.creator.id").value(1L))
                .andExpect(jsonPath("$.groupName").value("test groupName"))
                .andExpect(jsonPath("$.creatorUserName").value("test creatorUsername"));
        mockMvc.perform(put("/api/v1/groups/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.groupName").value("test groupName"));
        mockMvc.perform(patch("/api/v1/groups/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.groupName").value("test groupName"));
        mockMvc.perform(delete("/api/v1/groups/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupDTO)))
                .andExpect(status().isNoContent());
    }
    @Test
    public void testIntgrationsOfUserMsgAndUser() throws Exception {
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
        when(userMessageServise.createUserMsg(any(UserMessageDTO.class))).thenReturn(userMessageDTO);
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
        mockMvc.perform(get("/api/v1/usermsg/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userMessageDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messageId").value(3L))
                .andExpect(jsonPath("$.sender_id.id").value(1L))
                .andExpect(jsonPath("$.senderName").value("test username"))
                .andExpect(jsonPath("$.receiver_id.id").value(2L))
                .andExpect(jsonPath("$.receiverName").value("test username2"))
                .andExpect(jsonPath("$.messageContent").value("test messageContent"));
        mockMvc.perform(put("/api/v1/usermsg/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userMessageDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messageId").value(3L))
                .andExpect(jsonPath("$.messageContent").value("test messageContent"));
        mockMvc.perform(patch("/api/v1/usermsg/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userMessageDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messageId").value(3L))
                .andExpect(jsonPath("$.messageContent").value("test messageContent"));
        mockMvc.perform(delete("/api/v1/usermsg/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userMessageDTO)))
                .andExpect(status().isNoContent());
    }
    @Test
    public void testIntgrationsOfGroupMsgMsgAndGroup() throws Exception {
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

        GroupMessageDTO groupMessageDTO = new GroupMessageDTO();
        groupMessageDTO.setId(3L);
        groupMessageDTO.setGroup(group);
        groupMessageDTO.setGroupName(group.getGroupName());
        groupMessageDTO.setSender_id(user);
        groupMessageDTO.setSenderName(user.getUsername());
        groupMessageDTO.setMessageContent("test messageContent");
        when(groupMessageServise.createGroupMsg(any(GroupMessageDTO.class))).thenReturn(groupMessageDTO);
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
        mockMvc.perform(get("/api/v1/groupmsg/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupMessageDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.sender_id.id").value(1L))
                .andExpect(jsonPath("$.senderName").value("test username"))
                .andExpect(jsonPath("$.group.id").value(1L))
                .andExpect(jsonPath("$.groupName").value("test groupName"))
                .andExpect(jsonPath("$.messageContent").value("test messageContent"));
        mockMvc.perform(put("/api/v1/groupmsg/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupMessageDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.messageContent").value("test messageContent"));
        mockMvc.perform(patch("/api/v1/groupmsg/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupMessageDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.messageContent").value("test messageContent"));
        mockMvc.perform(delete("/api/v1/groupmsg/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupMessageDTO)))
                .andExpect(status().isNoContent());
    }
    @Test
    public void testIntgrationsOfGroupMembMsgAndGroup() throws Exception {
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
        when(groupMembersServise.createGroupMemb(any(GroupMembersDTO.class))).thenReturn(groupMembersDTO);
        mockMvc.perform(post("/api/v1/groupmemb")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupMembersDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.groupId.id").value(1L))
                .andExpect(jsonPath("$.groupName").value("test groupName"))
                .andExpect(jsonPath("$.userId.id").value(1L))
                .andExpect(jsonPath("$.userName").value("test username"));
        mockMvc.perform(get("/api/v1/groupmemb/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupMembersDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.groupId.id").value(1L))
                .andExpect(jsonPath("$.groupName").value("test groupName"))
                .andExpect(jsonPath("$.userId.id").value(1L))
                .andExpect(jsonPath("$.userName").value("test username"));
        mockMvc.perform(delete("/api/v1/groupmemb/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupMembersDTO)))
                .andExpect(status().isNoContent());
    }


}
