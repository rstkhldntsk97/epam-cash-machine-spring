package com.epam.springcashmachine.controller;

import com.epam.springcashmachine.api.UserApi;
import com.epam.springcashmachine.controller.assembler.UserAssembler;
import com.epam.springcashmachine.controller.model.UserModel;
import com.epam.springcashmachine.dto.UserDto;
import com.epam.springcashmachine.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;
    private final UserAssembler userAssembler;

    @Override
    public UserModel getUser(String username) {
        UserDto outUserDto = userService.getUser(username);
        return userAssembler.toModel(outUserDto);
    }

    @Override
    public UserModel createUser( UserDto userDto) {
        UserDto outUserDto = userService.createUser(userDto);
        return userAssembler.toModel(outUserDto);
    }

    @Override
    public UserModel updateUser(String username,UserDto userDto) {
        UserDto outUserDto = userService.updateUser(username, userDto);
        return userAssembler.toModel(outUserDto);
    }

    @Override
    public ResponseEntity<Void> deleteUser(String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    @Override

    public List<UserModel> getAll() {
        List<UserDto> outUsers = userService.getAll();
        List<UserModel> users = new ArrayList<>();
        for (UserDto userDto : outUsers) {
            users.add(userAssembler.toModel(userDto));
        }
        return users;
    }

}
