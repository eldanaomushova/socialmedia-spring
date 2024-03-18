package com.example.socialmediaapp.services;

import com.example.socialmediaapp.entities.GroupMembers;

public interface GroupMembersServise {
    public GroupMembers getGroupMembersById(Long id);
    public Iterable<GroupMembers> getAllGroupMembersById();
    public GroupMembers createGroupMembers(GroupMembers groupMembers);
    public void deleteGroupMember(Long id);
}
