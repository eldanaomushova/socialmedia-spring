package com.example.socialmediaapp.controllers;

import com.example.socialmediaapp.dto.GroupDTO;
import com.example.socialmediaapp.entities.Group;
import com.example.socialmediaapp.repositories.GroupRepository;
import com.example.socialmediaapp.services.GroupServise;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/groups")
public class GroupApiController {
    private final GroupServise groupServise;
    private final GroupRepository groupRepository;
    @GetMapping
    public ResponseEntity<List<GroupDTO>> getAllGroups() {
        List<GroupDTO> groupsDTO = groupServise.getAllGroupsOfUser();
        return ResponseEntity.ok(groupsDTO);
    }
    @GetMapping("/{id}")
    public GroupDTO getGroup(@PathVariable Long id) {
        return groupServise.getGroupById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Resource with id:%d Not Found", id)
                ));
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
    public ResponseEntity<GroupDTO> updateGroup(@PathVariable Long id, @RequestBody GroupDTO updatedGroup) {
        Optional<GroupDTO> updatedGroupOptional = groupServise.updateGroupNameById(id, updatedGroup);
        if (updatedGroupOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedGroupOptional.get());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupServise.deleteGroupById(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Group> updateGroup2(@PathVariable Long id, @RequestBody Group updatedGroup){
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if(optionalGroup.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Group existingGroup = optionalGroup.get();
        if(updatedGroup.getGroupName()!=null){
            existingGroup.setGroupName((updatedGroup.getGroupName()));
        }
        Group savedGroup = groupRepository.save(existingGroup);
        return ResponseEntity.ok(savedGroup);
    }
}

