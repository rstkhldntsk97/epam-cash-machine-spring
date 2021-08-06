package com.epam.springcashmachine.api;

import com.epam.springcashmachine.controller.model.UserModel;
import com.epam.springcashmachine.dto.UserDto;
import com.epam.springcashmachine.dto.group.OnCreate;
import com.epam.springcashmachine.dto.group.OnUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "User management API")
@RequestMapping("/api/v1/users")
public interface UserApi {

    @ApiImplicitParams({@ApiImplicitParam(name = "username", paramType = "path", required = true, value = "Username")})
    @ApiOperation("Get user")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/get/{username}")
    UserModel getUser(@PathVariable String username);

    @ApiOperation("Create user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/createUser")
    UserModel createUser(@Validated(OnCreate.class) @RequestBody UserDto userDto);

    @ApiImplicitParams({@ApiImplicitParam(name = "username", paramType = "path", required = true, value = "Username")})
    @ApiOperation("Update user")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping( "/update/{username}")
    UserModel updateUser(@PathVariable String username, @Validated(OnUpdate.class) @RequestBody UserDto userDto);

    @ApiImplicitParams({@ApiImplicitParam(name = "username", paramType = "path", required = true, value = "Username")})
    @ApiOperation("Delete user")
    @DeleteMapping( "/delete/{username}")
    ResponseEntity<Void> deleteUser(@PathVariable String username);

    @ApiOperation("Get all users")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    List<UserModel> getAll();



}
