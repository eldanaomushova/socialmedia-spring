package com.example.socialmediaapp.repositories;

import com.example.socialmediaapp.entities.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("postgres")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    void getAllPublishers() {
        List<User> users = (List<User>) userRepository.findAll();
        User x = users.get(0);

        System.out.println(x.getId());
        System.out.println(x.getUsername());
        x.setUsername("Updated test UserName");

        userRepository.save(x);
        System.out.println(x.getUsername());
        System.out.println("size: " + x.getGroupSet().size());

    }
    @Test
    void findAll() {
        List<User> users = (List<User>) userRepository.findAll();
        assertEquals(10002, users.size());
    }
    @AfterEach
    void tearDown() {
        List<User> users = (List<User>) userRepository.findAll();
        for (User user : users) {
            System.out.println(user.getId());
            System.out.println(user.getUsername());
        }
    }
    @Test
    void lazyVsEager() {
        List<User> users = (List<User>) userRepository.findAll();
        User user = users.get(0);
        System.out.println("We have got users: " + user.getUsername());
    }
}
