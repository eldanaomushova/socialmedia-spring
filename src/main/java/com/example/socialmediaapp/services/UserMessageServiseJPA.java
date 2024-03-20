package com.example.socialmediaapp.services;

import com.example.socialmediaapp.dto.UserMessageDTO;
import com.example.socialmediaapp.entities.UserMessage;
import com.example.socialmediaapp.mappers.UserMessageMapper;
import com.example.socialmediaapp.repositories.UserMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserMessageServiseJPA implements UserMessageServise{
    private final UserMessageRepository userMessageRepository;
    private final UserMessageMapper userMessageMapper;
    @Override
    public Optional<UserMessageDTO> getUserMsgById(Long id) {
        return Optional.ofNullable(
                userMessageMapper.userMessagetoUserMessageDTO(
                        userMessageRepository.findById(id).orElse(null)
                )
        );
    }
    @Override
    public UserMessageDTO createUserMsg(UserMessageDTO newUserMsg) {
        return userMessageMapper.userMessagetoUserMessageDTO(
                userMessageRepository.save(userMessageMapper.userMessageDTOtoUserMessage(newUserMsg))
        );
    }
    @Override
    public List<UserMessageDTO> getAllUserMsg() {
        List<UserMessage> books = (List<UserMessage>) userMessageRepository.findAll();
        return books.stream()
                .map(userMessageMapper::userMessagetoUserMessageDTO)
                .collect(Collectors.toList());
    }
    @Override
    public Optional<UserMessageDTO> deleteMsgById(Long id) {
        userMessageRepository.deleteById(id);
        return Optional.empty();
    }
    @Override
    public Optional<UserMessageDTO> updateMsgById(Long id) {
        return Optional.empty();
    }
}
