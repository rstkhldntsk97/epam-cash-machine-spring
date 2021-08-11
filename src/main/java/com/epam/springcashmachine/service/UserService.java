package com.epam.springcashmachine.service;

import com.epam.springcashmachine.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUser(String username);

    List<UserDto> getAll();

    void deleteUser(String username);

    UserDto updateUser(String username, UserDto userDto);

}
