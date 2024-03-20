package com.example.socialmediaapp.mappers;

import com.example.socialmediaapp.dto.GroupDTO;
import com.example.socialmediaapp.entities.Group;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface GroupMapper {
//    @Mapping(target = "username.groupSet", ignore = true)
    Group groupDTOToGroup(GroupDTO dto);

//    @Mapping(target = "username.groupSet", ignore = true)
    GroupDTO groupToGroupDTO(Group group);
}
