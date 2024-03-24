package com.example.socialmediaapp.controllers;

import com.example.socialmediaapp.dto.GroupDTO;
import com.example.socialmediaapp.entities.Group;
import com.example.socialmediaapp.entities.User;
import com.example.socialmediaapp.mappers.GroupMapper;
import com.example.socialmediaapp.services.GroupServise;
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
public class GroupApiControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    @Autowired
    MockHttpSession session;
    @Autowired
    GroupMapper groupMapper;
    @Mock
    private GroupServise groupServise;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void getAllGroups() throws Exception {
        mockMvc.perform(get("/api/v1/groups"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].groupName").exists())
                .andExpect(jsonPath("$[0].creator").exists());
    }
    @Test
    public void getGroup() throws Exception {
        mockMvc.perform(get("/api/v1/groups/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.groupName").value("test groupName"))
                .andExpect(jsonPath("$.creator.id").value(1L))
                .andExpect(jsonPath("$.creatorUserName").value("mike_time"));
    }
    @Test
    public void createGroup() throws Exception {
        User user = new User();
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
    }
    @Test
    public void updateGroup() throws Exception {
        User user = new User(1L, "mike_time", "mike@gmail.com", "1425e");
        Group updatedGroup = new Group(1L, "test groupName", user, "test creatorUsername");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/groups/{id}", 1L)
                        .content(objectMapper.writeValueAsString(updatedGroup))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.groupName").value("test groupName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.creator.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.creatorUserName").value("mike_time"));
    }
    @Test
    public void testDeleteGroupWithCascade() throws Exception {
        doThrow(DataIntegrityViolationException.class).when(groupServise).deleteGroupById(2L);
        mockMvc.perform(delete("/api/v1/groups/{id}", 2L))
                .andExpect(status().isNoContent());
    }
    @Test
    public void patchGroup2() throws Exception {
        User user = new User(1L, "mike_time", "mike@gmail.com", "1425e");
        Group updatedGroup = new Group(1L, "test groupName", user, "test creatorUsername");
        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/api/v1/groups/{id}", 1L)
                        .content(objectMapper.writeValueAsString(updatedGroup))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.groupName").value("test groupName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.creator.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.creatorUserName").value("mike_time"));
    }
}