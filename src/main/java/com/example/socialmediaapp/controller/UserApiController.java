package com.example.socialmediaapp.controller;

import com.example.socialmediaapp.dto.UserDTO;
import com.example.socialmediaapp.entities.User;
import com.example.socialmediaapp.mappers.UserMapper;
import com.example.socialmediaapp.repositories.UserRepository;
import com.example.socialmediaapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserApiController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping
    public Page<UserDTO> getUsers(
            @PageableDefault(
                    size = 25,
                    sort = {"email", "username"},
                    direction = Sort.Direction.DESC)
            Pageable pageable
    ) {

        System.out.println("pageable = " + pageable);
        return userService.getAllUsers(pageable);
    }

    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@Validated @RequestBody UserDTO newUser) {
        newUser.setId(null);
        UserDTO savedUser= userService.createUser(newUser);
        return ResponseEntity
                .created(URI.create("/api/v1/users/" + savedUser.getId()))
                .body(savedUser);
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        return userService.getUserById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Resource with id:%d Not Found", id)
                ));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        User existingUser = optionalUser.get();
        if(updatedUser.getUsername()!=null){
            existingUser.setUsername((updatedUser.getUsername()));
        }
        User savedUser = userRepository.save(existingUser);
        return ResponseEntity.ok(savedUser);
    }
}

