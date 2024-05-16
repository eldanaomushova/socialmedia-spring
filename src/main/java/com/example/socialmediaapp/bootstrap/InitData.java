package com.example.socialmediaapp.bootstrap;

import com.example.socialmediaapp.entities.*;
import com.example.socialmediaapp.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.Set;

@Log4j2
@Component
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final UserMessageRepository userMessageRepository;
    private final GroupMessageRepository groupMessageRepository;
    private final GroupMembersRepository groupMembersRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        log.atWarn().log("Initializing data");
        User user1 = User.builder()
                .email("mike@gmail.com")
                .password(passwordEncoder.encode("1425e"))
                .username("mike_time")
                .roles(Set.of(UserRole.admin))
                .build();
        User user2 = User.builder()
                .email("eldana.omushova@alatoo.edu.kg")
                .password(passwordEncoder.encode("412054Ma."))
                .username("eldana956")
                .roles(Set.of(UserRole.user))
                .build();
        userRepository.saveAll(List.of(user1, user2));
        generateUsers(100);
        Group group1 = Group.builder()
                .groupName("students information")
                .creator(user1)
                .creatorUserName(user1.getUsername())
                .build();
        Group group2 = Group.builder()
                .groupName("Classmates")
                .creator(user2)
                .creatorUserName(user2.getUsername())
                .build();
        groupRepository.saveAll(List.of(group1, group2));
        UserMessage userMessage = UserMessage.builder()
                .messageContent("Its me, hi")
                .sender_id(user2)
                .senderName(user2.getUsername())
                .receiver_id(user1)
                .receiverName(user1.getUsername())
                .build();
        UserMessage userMessage2 = UserMessage.builder()
                .messageContent("Hi, how are you?")
                .sender_id(user1)
                .senderName(user1.getUsername())
                .receiver_id(user2)
                .receiverName(user2.getUsername())
                .build();

        userMessageRepository.saveAll(List.of(userMessage, userMessage2));
        GroupMembers groupMembers1 = GroupMembers.builder()
                .groupId(group1)
                .groupName(group1.getGroupName())
                .userId(user1)
                .userName(user1.getUsername())
                .build();
        GroupMembers groupMembers2 = GroupMembers.builder()
                .groupId(group1)
                .groupName(group1.getGroupName())
                .userId(user2)
                .userName(user2.getUsername())
                .build();
        groupMembersRepository.saveAll(List.of(groupMembers1, groupMembers2));
        GroupMessage groupMessage = GroupMessage.builder()
                .messageContent("hi everyone")
                .sender_id(user2)
                .senderName(user2.getUsername())
                .group(group1)
                .groupName(group1.getGroupName())
                .build();
        GroupMessage groupMessage2 = GroupMessage.builder()
                .messageContent("What is the next lesson?")
                .sender_id(user1)
                .senderName(user1.getUsername())
                .group(group1)
                .groupName(group1.getGroupName())
                .build();
        groupMessageRepository.saveAll(List.of(groupMessage, groupMessage2));

    }
    public void generateUsers(int numberOfUsers) {

        Random random = new Random();

        for (int i = 1; i <= numberOfUsers; i++) {
            User book = User.builder()
                    .email("User " + i)
                    .password(String.valueOf(random.nextInt(100_000_000,999_999_999)))
                    .username("UserName " + i)
                    .build();

            userRepository.save(book);
        }
    }
}
