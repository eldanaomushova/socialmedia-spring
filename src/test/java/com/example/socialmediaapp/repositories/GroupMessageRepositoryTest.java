package com.example.socialmediaapp.repositories;

import com.example.socialmediaapp.entities.Group;
import com.example.socialmediaapp.entities.GroupMessage;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupMessageRepositoryTest {
    @Autowired
    GroupMessageRepository groupMessageRepository;

    @Test
    @Transactional
    void getAllGroups() {
        List<GroupMessage> groupMessagesList = (List<GroupMessage>) groupMessageRepository.findAll();
        GroupMessage x = groupMessagesList.get(0);

        System.out.println(x.getId());
        System.out.println(x.getMessageContent());
        x.setGroupName("Updated test Group Message");

        groupMessageRepository.save(x);
        System.out.println(x.getMessageContent());
    }

    @Test
    void findAll() {
        List<GroupMessage> groupMessagesList = (List<GroupMessage>) groupMessageRepository.findAll();
        assertEquals(2, groupMessagesList.size());
    }

    @AfterEach
    void tearDown() {
        List<GroupMessage> groupMessagesList = (List<GroupMessage>) groupMessageRepository.findAll();
        for (GroupMessage groupMessage : groupMessagesList) {
            System.out.println(groupMessage.getId());
            System.out.println(groupMessage.getMessageContent());
        }
    }

}
