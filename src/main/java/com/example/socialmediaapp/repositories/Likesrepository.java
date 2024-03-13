package com.example.socialmediaapp.repositories;

import com.example.socialmediaapp.entities.Likes;
import org.springframework.data.repository.CrudRepository;

public interface Likesrepository extends CrudRepository<Likes, Long> {
}
