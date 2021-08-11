package com.epam.springcashmachine.service.impl;

import com.epam.springcashmachine.dto.UserDto;
import com.epam.springcashmachine.model.Receipt;
import com.epam.springcashmachine.model.User;
import com.epam.springcashmachine.model.enums.Role;
import com.epam.springcashmachine.service.MappingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class MappingServiceImpl implements MappingService {

    private final PropertyUtilsBean PUB;

    @Override
    public UserDto mapUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        try {
            PUB.copyProperties(userDto, user);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            log.info("Mapping exception");
        }
        return userDto;
    }

    @Override
    public User mapUserDtoToUser(UserDto userDto) {
        User user = new User();
        try {
            PUB.copyProperties(user, userDto);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            log.info("Mapping exception");
        }
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
