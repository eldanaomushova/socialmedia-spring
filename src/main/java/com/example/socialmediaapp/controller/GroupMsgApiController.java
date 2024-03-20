package com.example.socialmediaapp.controller;
import com.example.socialmediaapp.dto.GroupMessageDTO;
import com.example.socialmediaapp.entities.GroupMessage;
import com.example.socialmediaapp.repositories.GroupMessageRepository;
import com.example.socialmediaapp.services.GroupMessageServise;
import lombok.RequiredArgsConstructor;
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
    private final GroupMessageRepository groupMessageRepository;
    @GetMapping
    public ResponseEntity<List<GroupMessageDTO>> getAllGroupMsg() {
        List<GroupMessageDTO> groupMemb = groupMessageServise.getAllGroupMsg();
        return ResponseEntity.ok(groupMemb);
    }
    @GetMapping("/{id}")
    public GroupMessageDTO getGroupMsg(@PathVariable Long id) {
        return groupMessageServise.getGroupMsgById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Resource with id:%d Not Found", id)
                ));
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
        Optional<GroupMessageDTO> updatedGroupMsgOptional = groupMessageServise.updateGroupMsgNameById(id, updatedGroupMsg);
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
    public ResponseEntity<GroupMessage> updateGroupMsg2(@PathVariable Long id, @RequestBody GroupMessage updatedGroupMsg){
        Optional<GroupMessage> optionalGroupMsg = groupMessageRepository.findById(id);
        if(optionalGroupMsg.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        GroupMessage existingGroupMsg = optionalGroupMsg.get();
        if(updatedGroupMsg.getMessageContent()!=null){
            existingGroupMsg.setMessageContent((updatedGroupMsg.getMessageContent()));
        }
        GroupMessage savedGroupMsg = groupMessageRepository.save(existingGroupMsg);
        return ResponseEntity.ok(savedGroupMsg);
    }
}
