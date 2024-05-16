package com.example.socialmediaapp.services;

import com.example.socialmediaapp.dto.UserDTO;
import com.example.socialmediaapp.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
public interface UserService {
    Optional<UserDTO> getUserById(Long id);

    Optional<User> getById(Long userId);

    UserDTO createUser(UserDTO newUser);

    Page<UserDTO> getAllUsers(Pageable pageable);
    Optional<UserDTO> deleteById(Long id);

    Optional<UserDTO> updateById(Long id, UserDTO updatedUserDTO);

    public String signUpUser(User appUser);
}
