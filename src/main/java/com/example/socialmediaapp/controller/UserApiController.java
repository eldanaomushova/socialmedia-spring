package com.example.socialmediaapp.controller;

import com.example.socialmediaapp.dto.UserDTO;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserApiController {

    @GetMapping
    public Page<UserDTO> getUsers(
            @PageableDefault(
                    size = 25,
                    sort = {"email", "username"},
                    direction = Sort.Direction.DESC)
            Pageable pageable
//            @RequestParam(required = false, defaultValue = "0") Integer page,
//            @RequestParam(required = false, defaultValue = "25") Integer size,
//            String[] sort
    ) {

        System.out.println("pageable = " + pageable);

        /*System.out.println("page = " + page);
        System.out.println("size = " + size);
        System.out.println("sort = " + String.join("; ",sort));*/

        return userService.getAllUsers(pageable);
    }

    private final UserService userService;

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
//                .orElseThrow(NotFoundException::new);
//                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }



}

