package com.epam.springcashmachine.repository.impl;

import com.epam.springcashmachine.model.User;
import com.epam.springcashmachine.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final List<User> user = new ArrayList<>();

    @Override
    public User getUser(String login) {
        return null;
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUser(String login, User user) {
        return null;
    }

    @Override
    public boolean deleteUser(String login) {
        return false;
    }

}
