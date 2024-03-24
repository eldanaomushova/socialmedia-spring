package com.example.socialmediaapp.controllers;

import com.example.socialmediaapp.dto.UserMessageDTO;
import com.example.socialmediaapp.entities.UserMessage;
import com.example.socialmediaapp.repositories.UserMessageRepository;
import com.example.socialmediaapp.services.UserMessageServise;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/usermsg")
public class UserMessagesApiController {
    private final UserMessageServise userMessageServise;
    private final UserMessageRepository userMessageRepository;
    @GetMapping
    public ResponseEntity<List<UserMessageDTO>> getAllUserMsg() {
        List<UserMessageDTO> userMsgDTO = userMessageServise.getAllUserMsg();
        return ResponseEntity.ok(userMsgDTO);
    }
    @PostMapping()
    public ResponseEntity<UserMessageDTO> createUserMsg(@Validated @RequestBody UserMessageDTO newUserMsg) {
        newUserMsg.setMessageId(null);
        UserMessageDTO savedUserMsg = userMessageServise.createUserMsg(newUserMsg);
        return ResponseEntity
                .created(URI.create("/api/v1/usermsg/" + savedUserMsg.getMessageId()))
                .body(savedUserMsg);
    }
    @GetMapping("/{id}")
    public UserMessageDTO getUserMsgById(@PathVariable Long id) {
        return userMessageServise.getUserMsgById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Resource with id:%d Not Found", id)
                ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserMsg(@PathVariable Long id) {
        userMessageServise.deleteMsgById(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<UserMessage> updateUserMsg(@PathVariable Long id, @RequestBody UserMessage updatedUserMsg){
        Optional<UserMessage> optionalUserMsg = userMessageRepository.findById(id);
        if(optionalUserMsg.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        UserMessage existingUserMsg = optionalUserMsg.get();
        if(updatedUserMsg.getMessageContent()!=null){
            existingUserMsg.setMessageContent((updatedUserMsg.getMessageContent()));
        }
        UserMessage savedUserMsg = userMessageRepository.save(existingUserMsg);
        return ResponseEntity.ok(savedUserMsg);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserMessageDTO> updateUserMsg(@PathVariable Long id, @RequestBody UserMessageDTO updatedUserMsgDTO) {
        Optional<UserMessageDTO> updatedUserMsgOptional = userMessageServise.updateMsgById(id, updatedUserMsgDTO);
        if (updatedUserMsgOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedUserMsgOptional.get());
    }
}
