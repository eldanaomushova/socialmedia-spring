package com.example.socialmediaapp.mappers;
import com.example.socialmediaapp.dto.UserMessageDTO;
import com.example.socialmediaapp.entities.UserMessage;
import org.mapstruct.Mapper;

@Mapper
public interface UserMessageMapper {
    UserMessage userMessageDTOtoUserMessage(UserMessageDTO userMessageDTO);
    UserMessageDTO userMessagetoUserMessageDTO(UserMessage userMessage);
}
