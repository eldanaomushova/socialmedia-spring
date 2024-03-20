package com.example.socialmediaapp.services;

import com.example.socialmediaapp.dto.UserMessageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserMessageServise {
    Optional<UserMessageDTO> getUserMsgById(Long id);
    UserMessageDTO createUserMsg(UserMessageDTO newUserMsg);

    List<UserMessageDTO> getAllUserMsg();
    Optional<UserMessageDTO> deleteMsgById(Long id);
    Optional<UserMessageDTO> updateMsgById(Long id);
}
