package com.example.socialmediaapp.services;

import com.example.socialmediaapp.entities.Group;
import com.example.socialmediaapp.entities.GroupMessage;
import com.example.socialmediaapp.repositories.GroupMessageRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@NoArgsConstructor
public class GroupMessageServiseJPA {
    @Autowired
    public GroupMessageRepository groupMessageRepository;

    public GroupMessage getGroupMessageById(Long id){
        return groupMessageRepository.findById(id).orElse(null);
    }
    public Iterable<GroupMessage> getAllGroupMessages(){
        return groupMessageRepository.findAll();
    }

    public GroupMessage createGroupMessage(GroupMessage groupMessage){
        return groupMessageRepository.save(groupMessage);
    }
    public GroupMessage updataGroupMessage(Long id, GroupMessage newGroupMessage){
        GroupMessage existingGroupMessage = groupMessageRepository.findById(id).orElse(null);
        if(existingGroupMessage != null){
            existingGroupMessage.setSender_id(newGroupMessage.getSender_id());
            existingGroupMessage.setMessageContent(newGroupMessage.getMessageContent());
            existingGroupMessage.setGroup(newGroupMessage.getGroup());
            return groupMessageRepository.save(existingGroupMessage);
        }
        return existingGroupMessage;
    }
    public void deleteGroupMessage(Long id){
        groupMessageRepository.deleteById(id);
    }
}
