package com.epam.springcashmachine.service.impl;

import com.epam.springcashmachine.dto.UserDto;
import com.epam.springcashmachine.exception.UserAlreadyExistsException;
import com.epam.springcashmachine.exception.UserNotFoundException;
import com.epam.springcashmachine.model.User;
import com.epam.springcashmachine.repository.UserRepository;
import com.epam.springcashmachine.service.MappingService;
import com.epam.springcashmachine.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MappingService mappingService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto getUser(String login) {
        User user = userRepository.findByUsername(login).orElseThrow(UserNotFoundException::new);
        log.info("getUser by email {}", login);
        return mappingService.mapUserToUserDto(user);
    }


    @SneakyThrows
    @Override
    public UserDto createUser(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new UserAlreadyExistsException();
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = mappingService.mapUserDtoToUser(userDto);
        user = userRepository.save(user);
        log.info("createUser by login {}", userDto.getUsername());
        return mappingService.mapUserToUserDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        List<UserDto> users = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            users.add(mappingService.mapUserToUserDto(user));
        }
        log.info("getAll users");
        return users;
    }

    @Override
    public UserDto login(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        if (user.getPassword().equals(password)) {
            log.info("user with username {} was successfully logged in", username);
            return mappingService.mapUserToUserDto(user);
        }
        throw new RuntimeException("cannot find user");
    }

    @Override
    public void deleteUser(String login) {
        User user = userRepository.findByUsername(login).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
        log.info("deleteUser by username {}", login);
    }

    @Override
    public UserDto updateUser(String username, UserDto userDto) {
        log.info("updateUser with username {}", username);
        User persistedUser = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        persistedUser = mappingService.populateUserWithPresentUserDtoFields(persistedUser, userDto);
        User storedUser = userRepository.save(persistedUser);
        log.info("User with username {} username was successfully updated", storedUser.getUsername());
        return mappingService.mapUserToUserDto(persistedUser);
    }

}
