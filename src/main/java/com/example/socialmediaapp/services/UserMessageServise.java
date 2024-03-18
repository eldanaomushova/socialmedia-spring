package com.example.socialmediaapp.services;

import com.example.socialmediaapp.entities.UserMessage;

import java.util.List;

public interface UserMessageServise {


    public UserMessage getUserMessageById(Long id);

    public Iterable<UserMessage> getAllUserMessages();

    public UserMessage createUserMessage(UserMessage userMessage);

    public UserMessage updateUserMessage(Long id, UserMessage newUserMessage);
    public void deleteUserMessage(Long id);
}
