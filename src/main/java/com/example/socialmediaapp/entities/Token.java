package com.example.socialmediaapp.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String token;
    private Date expirationDate;

    public Token(String username, String token, Date expirationDate) {
        this.username = username;
        this.token = token;
        this.expirationDate = expirationDate;
    }
}

