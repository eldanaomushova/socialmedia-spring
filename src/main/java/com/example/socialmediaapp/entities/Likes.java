package com.example.socialmediaapp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Likes {
    @Id
    @GeneratedValue
    private Long id;
//    @ManyToOne
//    private User user;
    @ManyToOne
    private Post post;
}
