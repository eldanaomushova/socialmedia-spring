package com.example.socialmediaapp.repositories;

import com.example.socialmediaapp.entities.User;
import com.example.socialmediaapp.entities.UserMessage;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserMessageRepositoryTest {
    @Autowired
    UserMessageRepository userMessageRepository;

    @Test
    @Transactional
    void getAllUserMessages(){
        List<UserMessage> userMessageList =(List<UserMessage>) userMessageRepository.findAll();
        UserMessage x = userMessageList.get(0);

        System.out.println(x.getMessageId());
        System.out.println(x.getMessageContent());
        x.setMessageContent("Updated test Message Content");

        userMessageRepository.save(x);
        System.out.println(x.getMessageContent());
    }

    @Test
    void findAll() {
        List<UserMessage> userMessageList = (List<UserMessage>) userMessageRepository.findAll();
        assertEquals(1, userMessageList.size());
    }
    @AfterEach
    void tearDown() {
        List<UserMessage> userMessageList = (List<UserMessage>) userMessageRepository.findAll();
        for (UserMessage userMessage : userMessageList) {
            System.out.println(userMessage.getMessageId());
            System.out.println(userMessage.getMessageContent());
        }
    }
}