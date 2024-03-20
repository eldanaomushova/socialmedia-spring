package com.example.socialmediaapp.mappers;

import com.example.socialmediaapp.dto.UserDTO;
import com.example.socialmediaapp.entities.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper
public interface UserMapper {
    @Mapping(target = "groupSet", ignore = true)
    User userDtoToUser(UserDTO dto);
    @Mapping(target = "groupSet", ignore = true)
    UserDTO userToUserDto(User user);
}
