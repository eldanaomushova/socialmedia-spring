package com.example.socialmediaapp.mappers;
import com.example.socialmediaapp.dto.GroupMessageDTO;
import com.example.socialmediaapp.dto.UserDTO;
import com.example.socialmediaapp.dto.UserMessageDTO;
import com.example.socialmediaapp.entities.GroupMessage;
import com.example.socialmediaapp.entities.User;
import com.example.socialmediaapp.entities.UserMessage;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper
public interface UserMessageMapper {
    UserMessage userMessageDTOtoUserMessage(UserMessageDTO userMessageDTO);
    @InheritInverseConfiguration
    UserMessage userMessagetoUserMessageDTO(UserMessage userMessage);
}
