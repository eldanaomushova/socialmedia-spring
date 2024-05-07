package com.example.socialmediaapp.controllers;

import com.example.socialmediaapp.token.RegisterService;
import com.example.socialmediaapp.token.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registerService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registerService.confirmToken(token);
    }

}