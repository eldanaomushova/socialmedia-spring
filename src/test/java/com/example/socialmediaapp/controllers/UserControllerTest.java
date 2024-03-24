package com.example.socialmediaapp.controllers;


import com.example.socialmediaapp.dto.GroupDTO;
import com.example.socialmediaapp.dto.UserDTO;
import com.example.socialmediaapp.entities.User;
import com.example.socialmediaapp.mappers.UserMapper;
import com.example.socialmediaapp.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    @Autowired
    MockHttpSession session;
    @Autowired
    UserMapper userMapper;
    @Mock
    private UserService userService;

    private ObjectMapper objectMapper = new ObjectMapper();
    @InjectMocks
    private UserApiController userApiController;

    @Test
    public void createUserTest() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("test username");
        userDTO.setEmail("test email");
        userDTO.setPassword("test password");
        when(userService.createUser(any(UserDTO.class))).thenReturn(userDTO);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userApiController).build();
        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("test username"))
                .andExpect(jsonPath("$.email").value("test email"))
                .andExpect(jsonPath("$.password").value("test password"));
    }
    @Test
    public void getUsersByIdTest() throws Exception {
        when(userService.getUserById(1L)).thenReturn(Optional.of(new UserDTO()));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userApiController).build();
        mockMvc.perform(get("/api/v1/users/{id}", 1L))
                .andExpect(status().isOk());
    }
    @Test
    public void deleteUserByIdTest() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userApiController).build();
        mockMvc.perform(delete("/api/v1/users/{id}", 3L))
                .andExpect(status().isNoContent());
    }
    @Test
    public void updateUserTest() throws Exception {
        UserDTO updatedUser = new UserDTO();
        updatedUser.setUsername("testUsername");
        when(userService.updateById(1L, updatedUser)).thenReturn(Optional.of(updatedUser));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userApiController).build();

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/users/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUser))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("testUsername"));
    }
    @Test
    public void updateUserTest2() throws Exception {
        UserDTO updatedUser = new UserDTO();
        updatedUser.setUsername("testUsername");
        when(userService.updateById(1L, updatedUser)).thenReturn(Optional.of(updatedUser));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userApiController).build();

        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/api/v1/users/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUser))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("testUsername"));
    }
}