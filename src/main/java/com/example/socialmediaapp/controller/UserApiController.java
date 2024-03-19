package com.example.socialmediaapp.controller;

import com.example.socialmediaapp.dto.UserDTO;
import com.example.socialmediaapp.entities.User;
import com.example.socialmediaapp.repositories.UserRepository;
import com.example.socialmediaapp.services.UserServiceJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserApiController {
    private final UserServiceJPA userServiceJPA;
    private final UserRepository userRepository;
    @GetMapping
    public ResponseEntity<Iterable<User>> getAllUsers(){
        Iterable<User> users = userServiceJPA.getAllUsers();
        return ResponseEntity.ok(users);
    }
//    @GetMapping("{id}")
//    public UserDTO getUser(@PathVariable Long id) {
//        User user = userServiceJPA.getUserById(id)
//                .orElseThrow(() -> new NotFoundException(
//                        String.format("Resource with id:%d Not Found", id)
//                ));
//    }

    @DeleteMapping("{id}")
    public UserDTO deleteBook(@PathVariable Long id) {
        Optional<User> optionalBook = userRepository.findById(id);
        userRepository.deleteById(id);
        return null;
    }
    @PutMapping("{id}")
    public ResponseEntity<User> updateTask(@PathVariable Long id, @RequestBody User updatedUser) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedUser.setId(id);
        return ResponseEntity.ok().body(userRepository.save(updatedUser));
    }
    @PatchMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User existingUser = optionalUser.get();

        if (updatedUser.getEmail() != null) {
            existingUser.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getPassword() != null) {
            existingUser.setPassword(updatedUser.getPassword());
        }
        if (updatedUser.getUsername() != null) {
            existingUser.setUsername(updatedUser.getUsername());
        }
        User savedUser = userRepository.save(existingUser);
        return ResponseEntity.ok(savedUser);
    }
    @PostMapping
    public User createUser(@RequestBody User user) {
        System.out.println("before save: " + user.toString());
        return userRepository.save(user);
    }
}
