package com.epam.springcashmachine.repository.impl;

import com.epam.springcashmachine.exception.UserNotFoundException;
import com.epam.springcashmachine.model.User;
import com.epam.springcashmachine.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final List<User> users = new ArrayList<>();

    @Override
    public User getUser(String login) {
        return users.stream()
                .filter(user -> user.getUsername().equals(login))
                .findFirst()
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User createUser(User user) {
        users.add(user);
        return user;
    }

    @Override
    public User updateUser(String login, User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deleteUser(String login) {
        return users.removeIf(user -> user.getUsername().equals(login));
    }

    @Override
    public List<User> getAll(){
        return users;
    }

}
