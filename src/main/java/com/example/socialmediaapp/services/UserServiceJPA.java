package com.example.socialmediaapp.services;

import com.example.socialmediaapp.dto.UserDTO;
import com.example.socialmediaapp.entities.User;
import com.example.socialmediaapp.mappers.UserMapper;
import com.example.socialmediaapp.repositories.UserRepository;
import com.example.socialmediaapp.token.confirmation.ConfirmationToken;
import com.example.socialmediaapp.token.confirmation.ConfirmationTokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceJPA implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        return Optional.ofNullable(
                userMapper.userToUserDto(
                        userRepository.findById(id).orElse(null)
                )
        );
    }
    @Override
    public Optional<User> getById(Long userId){
        return userRepository.findById(userId);
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

    @Override
    public String signUpUser(User appUser) {
        boolean userExists = userRepository
                .findByEmail(appUser.getEmail())
                .isPresent();
        if (userExists) {
            throw new IllegalStateException("email already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        userRepository.save(appUser);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );
        confirmationTokenService.saveConfirmationToken(
                confirmationToken);
        return token;
    }
    public int enableAppUser(String email) {
        return userRepository.enableAppUser(email);
    }
}