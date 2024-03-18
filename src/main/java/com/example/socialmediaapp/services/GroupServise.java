package com.example.socialmediaapp.services;

import com.example.socialmediaapp.entities.Group;
import com.example.socialmediaapp.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

public interface GroupServise {
    public Group getGroupById(Long id) ;
    public Iterable<Group> getAllGroups() ;
    public Group createGroup(Group group) ;
    public Group updateGroup(Long id, Group newGroup);
    public void deleteGroup(Long id);
}

