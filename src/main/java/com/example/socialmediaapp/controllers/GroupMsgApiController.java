package com.example.socialmediaapp.controllers;
import com.example.socialmediaapp.dto.GroupMessageDTO;
import com.example.socialmediaapp.entities.GroupMessage;
import com.example.socialmediaapp.repositories.GroupMessageRepository;
import com.example.socialmediaapp.services.GroupMessageServise;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/groupmsg")
public class GroupMsgApiController {
    private final GroupMessageServise groupMessageServise;
    @GetMapping
    public ResponseEntity<List<GroupMessageDTO>> getAllGroupMsg() {
        List<GroupMessageDTO> groupMsgDTO = groupMessageServise.getAllGroupMsg();
        return ResponseEntity.ok(groupMsgDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGroupMsg(@PathVariable Long id) {
        try {
            GroupMessageDTO groupMessageDTO = groupMessageServise.getGroupMsgById(id)
                    .orElseThrow(() -> new NotFoundException(
                            String.format("Resource with id:%d Not Found", id)
                    ));
            return ResponseEntity.ok(groupMessageDTO);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @PostMapping()
    public ResponseEntity<GroupMessageDTO> createGroupMsg(@Validated @RequestBody GroupMessageDTO newGroupMsg) {
        newGroupMsg.setId(null);
        GroupMessageDTO savedGroupMsg = groupMessageServise.createGroupMsg(newGroupMsg);
        return ResponseEntity
                .created(URI.create("/api/v1/groupmsg/" + savedGroupMsg.getId()))
                .body(savedGroupMsg);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupMessageDTO> updateGroupMsg(@PathVariable Long id, @RequestBody GroupMessageDTO updatedGroupMsg) {
        Optional<GroupMessageDTO> updatedGroupMsgOptional = groupMessageServise.updateGroupMsgById(id, updatedGroupMsg);
        if (updatedGroupMsgOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedGroupMsgOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroupMsg(@PathVariable Long id) {
        groupMessageServise.deleteGroupMsgById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GroupMessageDTO> updateGroupMsg2(@PathVariable Long id, @RequestBody GroupMessageDTO updatedGroupMsg) {
        Optional<GroupMessageDTO> updatedGroupMsgOptional = groupMessageServise.updateGroupMsgById(id, updatedGroupMsg);
        if (updatedGroupMsgOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedGroupMsgOptional.get());
    }

}
