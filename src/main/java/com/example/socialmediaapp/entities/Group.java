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


//public class User {
//    @Id
//    @EqualsAndHashCode.Include
//    @GeneratedValue
//    private Long id;
//    private String username;
//    @NotNull
//    @NotBlank
//    @Column(nullable = false)
//    private String email;
//    private String password;
//
//    @OneToMany(mappedBy = "username", fetch = FetchType.LAZY)
//    @JsonIgnore
//    private List<Group> groupSet;
//}