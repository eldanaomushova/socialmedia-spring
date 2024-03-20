package com.example.socialmediaapp.services;

import com.example.socialmediaapp.dto.GroupDTO;
import com.example.socialmediaapp.entities.Group;
import com.example.socialmediaapp.entities.User;
import com.example.socialmediaapp.mappers.GroupMapper;
import com.example.socialmediaapp.repositories.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupServiceJPA implements GroupServise{
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    @Override
    public Optional<GroupDTO> getGroupById(Long id) {
        return Optional.ofNullable(
                groupMapper.groupToGroupDTO(
                        groupRepository.findById(id).orElse(null)
                )
        );
    }

    @Override
    public GroupDTO createGroup(GroupDTO newGroup) {
        return groupMapper.groupToGroupDTO(
                groupRepository.save(groupMapper.groupDTOToGroup(newGroup))
        );
    }


    @Override
    public List<GroupDTO> getAllGroupsOfUser() {
        List<Group> books = (List<Group>) groupRepository.findAll();
        return books.stream()
                .map(groupMapper::groupToGroupDTO)
                .collect(Collectors.toList());
    }


    @Override
    public Optional<GroupDTO> deleteGroupById(Long id) {
        groupRepository.deleteById(id);
        return Optional.empty();
    }

    @Override
    public Optional<GroupDTO> updateGroupNameById(Long id, GroupDTO updatedGroupDTO) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isEmpty()) {
            return Optional.empty();
        }
        Group group = optionalGroup.get();
        group.setGroupName(updatedGroupDTO.getGroupName());
        Group updatedGroup = groupRepository.save(group);
        return Optional.of(groupMapper.groupToGroupDTO(updatedGroup));
    }
}
