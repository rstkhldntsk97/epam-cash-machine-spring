package com.epam.springcashmachine.service.impl;

import com.epam.springcashmachine.dto.UserDto;
import com.epam.springcashmachine.model.User;
import com.epam.springcashmachine.model.enums.Role;
import com.epam.springcashmachine.service.MappingService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class MappingServiceImpl implements MappingService {

    private final PropertyUtilsBean PUB;

    @SneakyThrows
    public UserDto mapUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        PUB.copyProperties(userDto, user);
        return userDto;
    }

    @SneakyThrows
    public User mapUserDtoToUser(UserDto userDto) {
        User user = new User();
        PUB.copyProperties(user, userDto);
        return user;
    }

    @Override
    public User populateUserWithPresentUserDtoFields(User user, UserDto userDto) {
        Role role = userDto.getRole();
        if (Objects.nonNull(role)) {
            user.setRole(role);
        }
        if (Objects.nonNull(userDto.getUsername())) {
            user.setUsername(userDto.getUsername());
        }
        return user;
    }


}
