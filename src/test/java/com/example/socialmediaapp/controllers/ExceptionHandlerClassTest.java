package com.example.socialmediaapp.controllers;

import com.example.socialmediaapp.dto.UserDTO;
import com.example.socialmediaapp.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ExceptionHandlerClassTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private UserService userService;


    @InjectMocks
    private UserApiController userApiController;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void handleNotFoundException() {
        NotFoundException exception = new NotFoundException("Resource not found");
        ResponseEntity<String> response = userApiController.handleNotFoundException(exception);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Resource not found", response.getBody());
    }
    @Test
    void testHandleException() throws Exception {
        RuntimeException exception = new RuntimeException("Something went wrong");

        mockMvc.perform(get("/api/example"))
                .andExpect(status().isInternalServerError());
    }
    @Test
    void testGetUserById_ValidUserId() {
        long userId = 1L;
        UserDTO user1 = new UserDTO();
        user1.setId(userId);
        user1.setUsername("user1");
        when(userService.getUserById(userId)).thenReturn(Optional.of(user1));
        Optional<UserDTO> optionalUserDTO = userService.getUserById(userId);
        assertTrue(optionalUserDTO.isPresent());
        assertEquals(user1, optionalUserDTO.get());
    }
}
