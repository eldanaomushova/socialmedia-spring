package com.example.socialmediaapp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue
    private Long id;
    @NonNull private String name;
    private String description;
    private int publishedDay;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Likes> likes;
}
