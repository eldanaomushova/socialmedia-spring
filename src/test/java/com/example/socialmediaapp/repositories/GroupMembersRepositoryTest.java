package com.example.socialmediaapp.repositories;

import com.example.socialmediaapp.entities.GroupMembers;
import com.example.socialmediaapp.entities.GroupMessage;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class GroupMembersRepositoryTest {
    @Autowired
    GroupMembersRepository groupMembersRepository;

    @Test
    @Transactional
    void getAllGroups() {
        List<GroupMembers> groupMembersList = (List<GroupMembers>) groupMembersRepository.findAll();
        GroupMembers x = groupMembersList.get(0);

        System.out.println(x.getId());
        System.out.println(x.getUserName());
        x.setGroupName("Updated test Group Members");

        groupMembersRepository.save(x);
        System.out.println(x.getUserName());
    }

    @Test
    void findAll() {
        List<GroupMembers> groupMembersList = (List<GroupMembers>) groupMembersRepository.findAll();
        assertEquals(1, groupMembersList.size());
    }

    @AfterEach
    void tearDown() {
        List<GroupMembers> groupMembersList = (List<GroupMembers>) groupMembersRepository.findAll();
        for (GroupMembers groupMembers : groupMembersList) {
            System.out.println(groupMembers.getId());
            System.out.println(groupMembers.getUserName());
        }
    }

}
