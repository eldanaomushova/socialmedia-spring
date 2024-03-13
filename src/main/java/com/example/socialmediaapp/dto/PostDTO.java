package com.example.socialmediaapp.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private Long id;
    private String name;
    private String description;
    private int publishedDay;
    private LikesDTO likesDTO;
}
