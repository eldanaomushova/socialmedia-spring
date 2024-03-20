package com.example.socialmediaapp.mappers;
import com.example.socialmediaapp.dto.GroupDTO;
import com.example.socialmediaapp.dto.GroupMembersDTO;
import com.example.socialmediaapp.dto.UserDTO;
import com.example.socialmediaapp.entities.Group;
import com.example.socialmediaapp.entities.GroupMembers;
import com.example.socialmediaapp.entities.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper
public interface GroupMembersMapper {
    GroupMembers groupMembersDTOToGroupMembers(GroupMembersDTO dto);

    GroupMembersDTO groupMembersToGroupMembersDTO(GroupMembers group);
}
