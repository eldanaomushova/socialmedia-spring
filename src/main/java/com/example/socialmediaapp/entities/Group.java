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
    @Column(name = "group_id")
    private Long id;
    private String groupName;

    @ManyToOne
    @JoinColumn(name = "creator_userId")
    private User creator;

    private String creatorUserName;

}
