package com.example.socialmediaapp.controllers;


import com.example.socialmediaapp.dto.UserDTO;
import com.example.socialmediaapp.entities.User;
import com.example.socialmediaapp.mappers.UserMapper;
import com.example.socialmediaapp.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


//import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    @Autowired
    MockHttpSession session;
    @Autowired
    UserMapper userMapper;
    @Mock
    private UserService userService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void createUserTest() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("test username");
        userDTO.setEmail("test email");
        userDTO.setPassword("test password");
        when(userService.createUser(any(UserDTO.class))).thenReturn(userDTO);
        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("test username"))
                .andExpect(jsonPath("$.email").value("test email"))
                .andExpect(jsonPath("$.password").value("test password"));
    }
    @Test
    public void getUsersTest() throws Exception {
        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].id").exists());
    }
    @Test
    public void getUserByIdTest() throws Exception {
        mockMvc.perform(get("/api/v1/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("mike_time"))
                .andExpect(jsonPath("$.email").value("mike@gmail.com"))
                .andExpect(jsonPath("$.password").value("1425e"));
    }

    @Test
    public void deleteUserByIdTest() throws Exception {
        // Perform the delete request
        mockMvc.perform(delete("/api/v1/users/{id}", 5L))
                .andExpect(status().isNoContent());
    }
    @Test
    public void updateUserTest() throws Exception {
        User updatedUser = new User(2L, "username2", "email2@gmail.com", "password2");

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/users/{id}", 2L)
                        .content(objectMapper.writeValueAsString(updatedUser))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("username2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("email2@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("password2"));
    }
}
