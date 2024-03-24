package com.example.socialmediaapp.services;

import com.example.socialmediaapp.dto.UserDTO;
import com.example.socialmediaapp.entities.User;
import com.example.socialmediaapp.mappers.UserMapper;
import com.example.socialmediaapp.repositories.GroupRepository;
import com.example.socialmediaapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceJPA implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        return Optional.ofNullable(
                userMapper.userToUserDto(
                        userRepository.findById(id).orElse(null)
                )
        );
    }

    @Override
    public UserDTO createUser(UserDTO newUser) {
        return userMapper.userToUserDto(
                userRepository.save(userMapper.userDtoToUser(newUser))
        );
    }

    @Override
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        if (pageable.getPageSize()>1000) {
            pageable = PageRequest.of(pageable.getPageNumber(), 1000, pageable.getSort());
        }
        return userRepository.findAll(pageable).map(userMapper::userToUserDto);
    }

    @Override
    public Optional<UserDTO> deleteById(Long id) {
        userRepository.deleteById(id);
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> updateById(Long id, UserDTO updatedUserDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return Optional.empty();
        }
        User user = optionalUser.get();
        user.setUsername(updatedUserDTO.getUsername());
        user.setEmail(updatedUserDTO.getEmail());
        user.setPassword(updatedUserDTO.getPassword());
        User updatedUser = userRepository.save(user);
        return Optional.of(userMapper.userToUserDto(updatedUser));
    }

    private Pageable getPageable(Integer page, Integer size) {
        if (page == null || page < 0)
            page = 0;

        if (size == null || size <= 0)
            size = 25;

        if (size > 1000)
            size = 1000;

        Sort sort = Sort.by(
                Sort.Order.desc("email"),
                Sort.Order.asc("username")
        );

        return PageRequest.of(page, size, sort);
    }
}