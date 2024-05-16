package com.example.socialmediaapp.repositories;

import com.example.socialmediaapp.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByUsername(String username);
}