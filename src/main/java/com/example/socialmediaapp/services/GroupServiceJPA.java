package com.example.socialmediaapp.services;

import com.example.socialmediaapp.entities.Group;
import com.example.socialmediaapp.entities.UserMessage;
import com.example.socialmediaapp.repositories.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceJPA {
    @Autowired
    private GroupRepository groupRepository;

    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }

    public Iterable<Group>getAllGroups() {
        return groupRepository.findAll();
    }

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public Group updateGroup(Long id, Group newGroup) {
        Group existingGroup = groupRepository.findById(id).orElse(null);
        if (existingGroup != null) {
            existingGroup.setGroupName(newGroup.getGroupName());
            return groupRepository.save(existingGroup);
        }
        return null;
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
}
