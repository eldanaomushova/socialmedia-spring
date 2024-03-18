package com.example.socialmediaapp.services;

import com.example.socialmediaapp.entities.GroupMembers;
import com.example.socialmediaapp.repositories.GroupMembersRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class GroupMembersServiseJPA {
    @Autowired
    public GroupMembersRepository groupMembersRepository;
    public GroupMembers getGroupMembersById(Long id){
        return groupMembersRepository.findById(id).orElse(null);
    }

    public Iterable<GroupMembers> getAllGroupMembersById(){
        return groupMembersRepository.findAll();
    }
    public GroupMembers createGroupMembers(GroupMembers groupMembers){
        return groupMembersRepository.save(groupMembers);
    }
    public void deleteGroupMember(Long id){
        groupMembersRepository.deleteById(id);
    }

}
