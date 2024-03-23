package com.example.socialmediaapp.services;

import com.example.socialmediaapp.dto.UserDTO;
import com.example.socialmediaapp.entities.User;
import com.example.socialmediaapp.mappers.UserMapper;
import com.example.socialmediaapp.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceJPATest {
    @Autowired
    UserServiceJPA userServiceJPA;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Test
    void getUserById() {
        Long userId = 1L;
        Optional<UserDTO> result = userServiceJPA.getUserById(userId);
        assertTrue(result.isPresent());
        assertEquals(userId, result.get().getId());
        assertEquals("mike_time", result.get().getUsername());
        assertEquals("mike@gmail.com", result.get().getEmail());
        assertEquals("1425e", result.get().getPassword());
    }
    @Test
    void createUser() {
        UserDTO newUser = new UserDTO();
        newUser.setUsername("newUser");
        newUser.setEmail("newuser@example.com");
        newUser.setPassword("password");
        UserDTO createdUser = userServiceJPA.createUser(newUser);
        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());
        assertEquals("newUser", createdUser.getUsername());
        assertEquals("newuser@example.com", createdUser.getEmail());
        assertEquals("password", createdUser.getPassword());
    }
    @Test
    void getAllUsers() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "username"));
        Iterable<UserDTO> users = userServiceJPA.getAllUsers(pageable);
        assertNotNull(users, "Returned Iterable<UserDTO> is null");
    }
    @Test
    void deleteById_ForeignKeyViolation() {
        Long userId = 1L;
        assertThrows(DataIntegrityViolationException.class, () -> {
            userServiceJPA.deleteById(userId);
        }, "Deletion should fail due to foreign key constraint violation");
    }
    @Test
    void updateById() {
        Long userId = 1L;
        UserDTO updatedUserData = new UserDTO();
        updatedUserData.setUsername("updatedUser");
        updatedUserData.setEmail("updateduser@example.com");
        updatedUserData.setPassword("updatedPassword");
        Optional<UserDTO> updatedUser = userServiceJPA.updateById(userId, updatedUserData);
        assertTrue(updatedUser.isPresent());
        assertEquals(userId, updatedUser.get().getId());
        assertEquals("updatedUser", updatedUser.get().getUsername());
        assertEquals("updateduser@example.com", updatedUser.get().getEmail());
        assertEquals("updatedPassword", updatedUser.get().getPassword());
    }

}