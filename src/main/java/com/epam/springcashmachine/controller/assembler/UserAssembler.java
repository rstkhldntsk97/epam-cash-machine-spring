package com.epam.springcashmachine.controller.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.epam.springcashmachine.controller.UserController;
import com.epam.springcashmachine.controller.model.UserModel;
import com.epam.springcashmachine.dto.UserDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler extends RepresentationModelAssemblerSupport<UserDto, UserModel> {

    public static final String GET_REL = "get_user";
    public static final String CREATE_REL = "create_user";
    public static final String UPDATE_USER = "update_user";
    public static final String DELETE_USER = "delete_user";
    public static final String GET_ALL_USERS = "get_all_users";

    public UserAssembler() {
        super(UserController.class, UserModel.class);
    }

    @Override
    public UserModel toModel(UserDto entity) {
        UserModel userModel = new UserModel(entity);

        Link get = linkTo(methodOn(UserController.class).getUser(entity.getUsername())).withRel(GET_REL);
        Link create = linkTo(methodOn(UserController.class).createUser(entity)).withRel(CREATE_REL);
        Link update = linkTo(methodOn(UserController.class).updateUser(entity.getUsername(), entity)).withRel(UPDATE_USER);
        Link delete = linkTo(methodOn(UserController.class).deleteUser(entity.getUsername())).withRel(DELETE_USER);
        Link getAll = linkTo(methodOn(UserController.class).getAll()).withRel(GET_ALL_USERS);

        userModel.add(get, create, update, delete, getAll);

        return userModel;
    }

}