package com.example.socialmediaapp.mappers;

import com.example.socialmediaapp.dto.UserDTO;
import com.example.socialmediaapp.entities.User;
import com.example.socialmediaapp.entities.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashSet;
import java.util.Set;

@Mapper
public interface UserMapper {

    // ... (other mappings)

    @Mapping(target = "roles", ignore = true)
    User userDtoToUser(UserDTO dto);

    @Mapping(target = "roles", ignore = true)
    UserDTO userToUserDto(User user);

}