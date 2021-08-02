package com.epam.springcashmachine.service.impl;

import com.epam.springcashmachine.dto.UserDto;
import com.epam.springcashmachine.model.User;
import com.epam.springcashmachine.repository.UserRepository;
import com.epam.springcashmachine.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PropertyUtilsBean PUB;

    @Override
    public UserDto getUser(String login) {
        User user = userRepository.getUser(login);
        log.info("getUser by email {}", login);
        return mapUserToUserDto(user);
    }


    @SneakyThrows
    @Override
    public UserDto createUser(UserDto userDto) {
        if (!userDto.getPassword().equals(userDto.getRepeatPassword())){
            throw new RuntimeException("repeatPassword should be equals to password");
        }
        User user1 = new User();
        PUB.copyProperties(user1, userDto);
        User user = mapUserDtoToUser(userDto);
        user = userRepository.createUser(user);
        log.info("createUser by login {}", userDto.getUsername());
        return mapUserToUserDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        List<UserDto> users = new ArrayList<>();
        for (User user : userRepository.getAll()) {
            users.add(mapUserToUserDto(user));
        }
        log.info("getAll users");
        return users;
    }

    @Override
    public UserDto login(String username, String password) {
        User user = userRepository.getUser(username);
        if(user.getPassword().equals(password)) {
            log.info("user with username {} was successfully logged in", username);
            return mapUserToUserDto(user);
        }
        throw new RuntimeException("cannot username");
    }

    @Override
    public void deleteUser(String login) {
        userRepository.deleteUser(login);
        log.info("deleteUser by username {}", login);
    }

    @Override
    public UserDto updateUser(String email, UserDto userDto) {
        log.info("updateUser with email {}", email);
        User user = mapUserDtoToUser(userDto);

        User oldUser = userRepository.getUser(email);
        user.setEmail(oldUser.getEmail());
        user.setPassword(oldUser.getPassword());

        user = userRepository.updateUser(email, user);
        return mapUserToUserDto(user);
    }

    private UserDto mapUserToUserDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .role(user.getRole())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }

    private User mapUserDtoToUser(UserDto userDto) {
        return User.builder()
                .role(userDto.getRole())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }

}
