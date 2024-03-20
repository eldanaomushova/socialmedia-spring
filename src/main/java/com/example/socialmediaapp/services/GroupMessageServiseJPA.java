package com.example.socialmediaapp.services;

import com.example.socialmediaapp.dto.GroupMessageDTO;
import com.example.socialmediaapp.entities.GroupMessage;
import com.example.socialmediaapp.mappers.GroupMessageMapper;
import com.example.socialmediaapp.repositories.GroupMessageRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupMessageServiseJPA implements GroupMessageServise {
    private final GroupMessageRepository groupMsgRepository;
    private final GroupMessageMapper groupMsgMapper;

    @Override
    public Optional<GroupMessageDTO> getGroupMsgById(Long id) {
        return Optional.ofNullable(
                groupMsgMapper.groupMessagetoGroupMessageDTO(
                        groupMsgRepository.findById(id).orElse(null)
                )
        );
    }

    @Override
    public GroupMessageDTO createGroupMsg(GroupMessageDTO newGroupMsg) {
        return groupMsgMapper.groupMessagetoGroupMessageDTO(
                groupMsgRepository.save(groupMsgMapper.groupMessageDTOtoGroupMessage(newGroupMsg))
        );
    }

    @Override
    public List<GroupMessageDTO> getAllGroupMsg() {
        List<GroupMessage> groupMsg = (List<GroupMessage>) groupMsgRepository.findAll();
        return groupMsg.stream()
                .map(groupMsgMapper::groupMessagetoGroupMessageDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<GroupMessageDTO> deleteGroupMsgById(Long id) {
        groupMsgRepository.deleteById(id);
        return Optional.empty();
    }

    @Override
    public Optional<GroupMessageDTO> updateGroupMsgNameById(Long id, GroupMessageDTO updatedGroupMsgDTO) {
        Optional<GroupMessage> optionalGroupMsg = groupMsgRepository.findById(id);
        if (optionalGroupMsg.isEmpty()) {
            return Optional.empty();
        }
        GroupMessage groupMessage = optionalGroupMsg.get();
        groupMessage.setMessageContent(updatedGroupMsgDTO.getMessageContent());
        GroupMessage updatedGroupMsg = groupMsgRepository.save(groupMessage);
        return Optional.of(groupMsgMapper.groupMessagetoGroupMessageDTO(updatedGroupMsg));
    }
}
