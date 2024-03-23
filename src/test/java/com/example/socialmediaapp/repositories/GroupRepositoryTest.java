package com.example.socialmediaapp.repositories;

import com.example.socialmediaapp.entities.Group;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupRepositoryTest {
    @Autowired
    GroupRepository groupRepository;

    @Test
    @Transactional
    void getAllGroups() {
        List<Group> groupList = (List<Group>) groupRepository.findAll();
        Group x = groupList.get(0);

        System.out.println(x.getId());
        System.out.println(x.getGroupName());
        x.setGroupName("Updated test Group");

        groupRepository.save(x);
        System.out.println(x.getGroupName());
    }

    @Test
    void findAll() {
        List<Group> groupList = (List<Group>) groupRepository.findAll();
        assertEquals(1, groupList.size());
    }

    @AfterEach
    void tearDown() {
        List<Group> groupList = (List<Group>) groupRepository.findAll();
        for (Group group : groupList) {
            System.out.println(group.getId());
            System.out.println(group.getGroupName());
        }
    }

}
