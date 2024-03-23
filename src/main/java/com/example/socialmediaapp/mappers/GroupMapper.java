package com.example.socialmediaapp.mappers;

import com.example.socialmediaapp.dto.GroupDTO;
import com.example.socialmediaapp.entities.Group;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface GroupMapper {
    Group groupDTOToGroup(GroupDTO dto);

    GroupDTO groupToGroupDTO(Group group);
}
