package com.example.socialmediaapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@SpringBootApplication
public class SocialMediaAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialMediaAppApplication.class, args);
    }

}
