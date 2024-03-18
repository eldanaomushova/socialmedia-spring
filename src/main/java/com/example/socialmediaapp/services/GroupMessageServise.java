package com.example.socialmediaapp.services;

import com.example.socialmediaapp.entities.Group;
import com.example.socialmediaapp.entities.GroupMessage;

import java.util.List;

public interface GroupMessageServise {
    public GroupMessage getGroupMessageById(Long id);
    public Iterable<GroupMessage> getAllGroupMessages();

    public GroupMessage createGroupMessage(GroupMessage groupMessage);
    public GroupMessage updataGroupMessage(Long id, Group newGroupMessage);
    public void deleteGroupMessage(Long id);
}
