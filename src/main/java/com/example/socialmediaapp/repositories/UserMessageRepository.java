package com.example.socialmediaapp.repositories;

import com.example.socialmediaapp.entities.UserMessage;
import org.springframework.data.repository.CrudRepository;

public interface UserMessageRepository extends CrudRepository<UserMessage, Long> {
}
