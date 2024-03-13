package com.example.socialmediaapp.repositories;

import com.example.socialmediaapp.entities.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
