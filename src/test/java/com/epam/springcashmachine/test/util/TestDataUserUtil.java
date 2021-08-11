package com.epam.springcashmachine.test.util;

import com.epam.springcashmachine.dto.UserDto;
import com.epam.springcashmachine.model.User;
import com.epam.springcashmachine.model.enums.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDataUserUtil {

    public static final String USERNAME = "usernameTest";
    public static final Role CASHIER = Role.valueOf("CASHIER");
    public static final String PASSWORD = "password";

    public static User createUser() {
        return User.builder()
                .role(CASHIER)
                .username(USERNAME)
                .password(PASSWORD)
                .build();
    }

    public static UserDto createUserDto() {
        return UserDto.builder()
                .role(CASHIER)
                .username(USERNAME)
                .password(PASSWORD)
                .build();
    }

}
