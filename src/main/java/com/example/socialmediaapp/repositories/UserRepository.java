package com.example.socialmediaapp.repositories;

import com.example.socialmediaapp.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    Page<User> findAll(Pageable pageable);


}
