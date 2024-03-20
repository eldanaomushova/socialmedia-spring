package com.example.socialmediaapp.services;

import com.example.socialmediaapp.dto.GroupDTO;
import com.example.socialmediaapp.dto.GroupMessageDTO;
import com.example.socialmediaapp.entities.Group;
import com.example.socialmediaapp.entities.GroupMessage;

import java.util.List;
import java.util.Optional;

public interface GroupMessageServise {
    Optional<GroupMessageDTO> getGroupMsgById(Long id);
    GroupMessageDTO createGroupMsg(GroupMessageDTO newGroupMsg);

    List<GroupMessageDTO> getAllGroupMsg();
    Optional<GroupMessageDTO> deleteGroupMsgById(Long id);
    Optional<GroupMessageDTO> updateGroupMsgNameById(Long id, GroupMessageDTO updatedGroupMsgDTO);
}
