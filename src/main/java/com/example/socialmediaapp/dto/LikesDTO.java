package com.example.socialmediaapp.dto;

import com.example.socialmediaapp.entities.Post;
import com.example.socialmediaapp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikesDTO {
    private Long id;
    private User user;
    private Post post;
}
