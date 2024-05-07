package com.example.socialmediaapp.services;

import com.example.socialmediaapp.dto.UserDTO;
import com.example.socialmediaapp.entities.User;
import com.example.socialmediaapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
public interface UserService {
    Optional<UserDTO> getUserById(Long id);
    UserDTO createUser(UserDTO newUser);

    Page<UserDTO> getAllUsers(Pageable pageable);
    Optional<UserDTO> deleteById(Long id);

    Optional<UserDTO> updateById(Long id, UserDTO updatedUserDTO);

    public String signUpUser(User appUser);
}
