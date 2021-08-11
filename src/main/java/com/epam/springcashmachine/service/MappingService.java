package com.epam.springcashmachine.service;

import com.epam.springcashmachine.dto.UserDto;
import com.epam.springcashmachine.model.Receipt;
import com.epam.springcashmachine.model.User;

public interface MappingService {

    UserDto mapUserToUserDto(User user);

    User mapUserDtoToUser(UserDto userDto);

    User populateUserWithPresentUserDtoFields(User user, UserDto userDto);

}
