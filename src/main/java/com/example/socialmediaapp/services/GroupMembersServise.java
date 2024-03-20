package com.example.socialmediaapp.services;

import com.example.socialmediaapp.dto.GroupMembersDTO;
import com.example.socialmediaapp.entities.GroupMembers;

import java.util.List;
import java.util.Optional;

public interface GroupMembersServise {
    Optional<GroupMembersDTO> getGroupMembById(Long id);
    GroupMembersDTO createGroupMemb(GroupMembersDTO newGroupMemb);

    List<GroupMembersDTO> getAllGroupMemb();
    Optional<GroupMembersDTO> deleteGroupMembById(Long id);
}
