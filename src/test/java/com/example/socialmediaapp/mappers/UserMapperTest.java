package com.example.socialmediaapp.mappers;

import com.example.socialmediaapp.dto.UserDTO;
import com.example.socialmediaapp.entities.Group;
import com.example.socialmediaapp.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {
    @Autowired
    UserMapper userMapper;
    @Test
    void userToUserDTO() {
        User user = User.builder()
                .id(12L)
                .username("Test Username")
                .email("Test Email")
                .password("test password")
                .build();
        UserDTO userDTO = userMapper.userToUserDto(user);
        assertNotNull(userDTO);
        assertEquals("Test Username",userDTO.getUsername());
        assertEquals("Test Email", userDTO.getEmail());
        assertEquals("test password", userDTO.getPassword());
    }
    @Test
    void userDTOToUser() {
        UserDTO userDTO = UserDTO.builder()
                .id(12L)
                .username("Test Username")
                .email("Test Email")
                .password("test password")
                .build();
        User user = userMapper.userDtoToUser(userDTO);
        assertNotNull(user);
        assertEquals(12L, user.getId());
        assertEquals("Test Username", user.getUsername());
        assertEquals("Test Email", user.getEmail());
        assertEquals("test password", user.getPassword());
    }
}