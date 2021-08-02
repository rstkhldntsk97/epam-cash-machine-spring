package com.epam.springcashmachine.controller;

import com.epam.springcashmachine.api.UserApi;
import com.epam.springcashmachine.controller.assembler.UserAssembler;
import com.epam.springcashmachine.controller.model.UserModel;
import com.epam.springcashmachine.dto.UserDto;
import com.epam.springcashmachine.dto.group.OnCreate;
import com.epam.springcashmachine.dto.group.OnUpdate;
import com.epam.springcashmachine.model.User;
import com.epam.springcashmachine.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;
    private final UserAssembler userAssembler;

    @Override
    public UserModel getUser(@PathVariable String username) {
        UserDto outUserDto = userService.getUser(username);
        return userAssembler.toModel(outUserDto);
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/createUser")
    public UserModel createUser(@RequestBody @Validated(OnCreate.class) UserDto userDto) {
        UserDto outUserDto = userService.createUser(userDto);
        return userAssembler.toModel(outUserDto);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/users/{username}")
    public UserModel updateUser(@PathVariable String username,
                                @RequestBody @Validated(OnUpdate.class) UserDto userDto) {
        UserDto outUserDto = userService.updateUser(username, userDto);
        return userAssembler.toModel(outUserDto);
    }

    @Override

    public List<UserDto> getAll() {
        return userService.getAll();
    }

//    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public UserDto login(@RequestBody String username, @RequestBody String password) {
        return userService.login(username, password);
    }

    @Override
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

}
