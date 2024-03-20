package com.example.socialmediaapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "groups")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Group {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String groupName;

    @ManyToOne
    @JoinColumn(name = "creator_user_id")
    private User username;

}
