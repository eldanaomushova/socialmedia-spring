package com.example.socialmediaapp.mappers;
import com.example.socialmediaapp.dto.GroupMessageDTO;
import com.example.socialmediaapp.entities.GroupMessage;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper
public interface GroupMessageMapper {
    GroupMessage groupMessageDTOtoGroupMessage(GroupMessageDTO groupMessageDTO);
    GroupMessageDTO groupMessagetoGroupMessageDTO(GroupMessage groupMessage);
}
