package com.example.socialmediaapp.controllers;

import com.example.socialmediaapp.dto.UserDTO;
import com.example.socialmediaapp.entities.User;
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

    @GetMapping
    public Page<UserDTO> getUsers(
            @PathVariable
            @RequestParam
            @PageableDefault(
                    size = 25,
                    sort = {"email", "username"},
                    direction = Sort.Direction.DESC)
            Pageable pageable
    ) {

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
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO updatedUserDTO){
        Optional<UserDTO> updatedUserOptional = userService.updateById(id, updatedUserDTO);
        if (updatedUserOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedUserOptional.get());
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser2(@PathVariable Long id, @RequestBody UserDTO updatedUserDTO) {
        Optional<UserDTO> updatedUserOptional = userService.updateById(id, updatedUserDTO);
        if (updatedUserOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedUserOptional.get());
    }
}
