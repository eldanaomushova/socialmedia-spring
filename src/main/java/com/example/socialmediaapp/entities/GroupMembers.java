package com.example.socialmediaapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "group_members")
public class GroupMembers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numb_of_groupmemb")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group groupId;

    private String groupName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
    private String userName;

}

