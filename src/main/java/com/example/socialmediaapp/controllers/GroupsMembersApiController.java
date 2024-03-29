package com.example.socialmediaapp.controllers;
import com.example.socialmediaapp.dto.GroupMembersDTO;
import com.example.socialmediaapp.services.GroupMembersServise;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

        import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/groupmemb")
public class GroupsMembersApiController {
    private final GroupMembersServise groupMembersServise;
    @GetMapping
    public ResponseEntity<List<GroupMembersDTO>> getAllGroupMemb() {
        List<GroupMembersDTO> groupMembDTO = groupMembersServise.getAllGroupMemb();
        return ResponseEntity.ok(groupMembDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getGroupMemb(@PathVariable Long id) {
        try {
            GroupMembersDTO groupMembersDTO = groupMembersServise.getGroupMembById(id)
                    .orElseThrow(() -> new NotFoundException(
                            String.format("Resource with id:%d Not Found", id)
                    ));
            return ResponseEntity.ok(groupMembersDTO);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @PostMapping()
    public ResponseEntity<GroupMembersDTO> createGroupMemb(@Validated @RequestBody GroupMembersDTO newGroupMemb) {
        newGroupMemb.setId(null);
        GroupMembersDTO savedGroupMemb = groupMembersServise.createGroupMemb(newGroupMemb);
        return ResponseEntity
                .created(URI.create("/api/v1/groupmemb/" + savedGroupMemb.getId()))
                .body(savedGroupMemb);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroupMemb(@PathVariable Long id) {
        groupMembersServise.deleteGroupMembById(id);
        return ResponseEntity.noContent().build();
    }

}
