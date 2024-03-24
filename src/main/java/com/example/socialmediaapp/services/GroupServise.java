package com.example.socialmediaapp.services;

import com.example.socialmediaapp.dto.GroupDTO;

import java.util.List;
import java.util.Optional;

public interface GroupServise {
    Optional<GroupDTO> getGroupById(Long id);
    GroupDTO createGroup(GroupDTO newGroup);

    List<GroupDTO> getAllGroupsOfUser();
    Optional<GroupDTO> deleteGroupById(Long id);
    Optional<GroupDTO> updateGroupNameById(Long id, GroupDTO updatedGroupDTO);

}

