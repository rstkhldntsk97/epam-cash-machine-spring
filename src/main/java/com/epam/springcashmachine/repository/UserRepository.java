package com.epam.springcashmachine.repository;

import com.epam.springcashmachine.model.User;

import java.util.List;

public interface UserRepository {

    User getUser(String login);

    User createUser(User user);

    User updateUser(String login, User user);

    boolean deleteUser(String login);

    public List<User> getAll();

}
