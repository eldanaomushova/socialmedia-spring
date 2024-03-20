package com.example.socialmediaapp.controller;

import com.example.socialmediaapp.dto.GroupDTO;
import com.example.socialmediaapp.entities.Group;
import com.example.socialmediaapp.entities.User;
import com.example.socialmediaapp.mappers.GroupMapper;
import com.example.socialmediaapp.repositories.GroupRepository;
import com.example.socialmediaapp.services.GroupServise;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/groups")
public class GroupApiController {
    private final GroupServise groupServise;
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    @GetMapping
    public ResponseEntity<List<GroupDTO>> getAllBooks() {
        List<GroupDTO> books = groupServise.getAllGroupsOfUser();
        return ResponseEntity.ok(books);
    }
    @PostMapping()
    public ResponseEntity<GroupDTO> createGroup(@Validated @RequestBody GroupDTO newGroup) {
        newGroup.setId(null);
        GroupDTO savedGroup = groupServise.createGroup(newGroup);
        return ResponseEntity
                .created(URI.create("/api/v1/groups/" + savedGroup.getId()))
                .body(savedGroup);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Group> updateGroup2(@PathVariable Long id, @RequestBody Group updatedGroup) {
        if (!groupRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedGroup.setId(id);
        return ResponseEntity.ok().body(groupRepository.save(updatedGroup));
    }
}

