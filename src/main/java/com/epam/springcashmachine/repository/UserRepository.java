package com.epam.springcashmachine.repository;

import com.epam.springcashmachine.model.User;

public interface UserRepository {

    User getUser(String login);

    User createUser(User user);

    User updateUser(String login, User user);

    boolean deleteUser(String login);

}
