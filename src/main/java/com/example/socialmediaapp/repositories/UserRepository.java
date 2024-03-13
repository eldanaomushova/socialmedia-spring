package com.example.socialmediaapp.repositories;

import com.example.socialmediaapp.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
