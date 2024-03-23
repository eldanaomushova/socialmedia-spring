package com.example.socialmediaapp.services;

import com.example.socialmediaapp.dto.GroupMembersDTO;
import com.example.socialmediaapp.entities.Group;
import com.example.socialmediaapp.entities.GroupMembers;
import com.example.socialmediaapp.entities.GroupMessage;
import com.example.socialmediaapp.mappers.GroupMembersMapper;
import com.example.socialmediaapp.repositories.GroupMembersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GroupMembersServiseJPA implements GroupMembersServise{
    private final GroupMembersRepository groupMembersRepository;
    private final GroupMembersMapper groupMembersMapper;


    @Override
    public Optional<GroupMembersDTO> getGroupMembById(Long id) {
        Optional<GroupMembers> optionalGroupMembers = groupMembersRepository.findById(id);
        if (optionalGroupMembers.isPresent()) {
            return Optional.of(groupMembersMapper.groupMembersToGroupMembersDTO(optionalGroupMembers.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public GroupMembersDTO createGroupMemb(GroupMembersDTO newGroupMemb) {
        return groupMembersMapper.groupMembersToGroupMembersDTO(
                groupMembersRepository.save(groupMembersMapper.groupMembersDTOToGroupMembers(newGroupMemb))
        );
    }

    @Override
    public List<GroupMembersDTO> getAllGroupMemb() {
        List<GroupMembers> groupMsg = (List<GroupMembers>) groupMembersRepository.findAll();
        return groupMsg.stream()
                .map(groupMembersMapper::groupMembersToGroupMembersDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<GroupMembersDTO> deleteGroupMembById(Long id) {
        groupMembersRepository.deleteById(id);
        return Optional.empty();
    }


}
