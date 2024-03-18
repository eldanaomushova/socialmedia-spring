package com.example.socialmediaapp.services;

import com.example.socialmediaapp.dto.UserDTO;
import com.example.socialmediaapp.entities.User;
import com.example.socialmediaapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
public interface UserService {

    Optional<UserDTO>getUserById(Long id);

    public Iterable<User> getAllUsers() ;

    public User createUser(User user) ;

    public User updateUser(Long id, User newUser) ;
    public void deleteUser(Long id);
}
