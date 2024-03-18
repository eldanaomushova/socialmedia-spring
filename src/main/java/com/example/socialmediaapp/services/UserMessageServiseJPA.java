package com.example.socialmediaapp.services;

import com.example.socialmediaapp.entities.UserMessage;
import com.example.socialmediaapp.repositories.UserMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMessageServiseJPA {

    @Autowired
    private UserMessageRepository userMessageRepository;

    public UserMessage getUserMessageById(Long id) {
        return userMessageRepository.findById(id).orElse(null);
    }

    public Iterable<UserMessage> getAllUserMessages() {
        return userMessageRepository.findAll();
    }

    public UserMessage createUserMessage(UserMessage userMessage) {
        return userMessageRepository.save(userMessage);
    }

    public UserMessage updateUserMessage(Long id, UserMessage newUserMessage) {
        UserMessage existingUserMessage = userMessageRepository.findById(id).orElse(null);
        if (existingUserMessage != null) {
            existingUserMessage.setSender_id(newUserMessage.getSender_id());
            existingUserMessage.setReceiver_id(newUserMessage.getReceiver_id());
            existingUserMessage.setMessageContent(newUserMessage.getMessageContent());
            return userMessageRepository.save(existingUserMessage);
        }
        return null;
    }

    public void deleteUserMessage(Long id) {
        userMessageRepository.deleteById(id);
    }

}
