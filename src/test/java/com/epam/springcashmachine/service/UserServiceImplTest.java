package com.epam.springcashmachine.service;

import com.epam.springcashmachine.dto.UserDto;
import com.epam.springcashmachine.exception.UserAlreadyExistsException;
import com.epam.springcashmachine.exception.UserNotFoundException;
import com.epam.springcashmachine.model.User;
import com.epam.springcashmachine.repository.UserRepository;
import com.epam.springcashmachine.service.impl.MappingServiceImpl;
import com.epam.springcashmachine.service.impl.UserServiceImpl;
import com.epam.springcashmachine.test.util.TestDataUserUtil;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static com.epam.springcashmachine.test.util.TestDataUserUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Spy
    private final MappingService mappingService = new MappingServiceImpl(new PropertyUtilsBean());

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void getUserTest() {
        User user = TestDataUserUtil.createUser();
        when(userRepository.findByUsername(USERNAME)).thenReturn(Optional.of(user));

        UserDto userDto = userService.getUser(USERNAME);

        assertThat(userDto, allOf(
                hasProperty("username", equalTo(user.getUsername())),
                hasProperty("role", equalTo(user.getRole()))
        ));
    }

    @Test
    void getUserUserNotFoundTest() {
        when(userRepository.findByUsername(USERNAME)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getUser(USERNAME));
    }

    @Test
    public void createUserTest() {
        User testUser = TestDataUserUtil.createUser();
        UserDto testUserDto = TestDataUserUtil.createUserDto();
        when(userRepository.save(any())).thenReturn(testUser);
        when(mappingService.mapUserToUserDto(testUser)).thenReturn(testUserDto);
        when(passwordEncoder.encode(testUser.getPassword())).thenReturn(testUserDto.getPassword());

        UserDto userDto = userService.createUser(testUserDto);

        assertThat(userDto, allOf(
                hasProperty("role", equalTo(testUser.getRole())),
                hasProperty("username", equalTo(testUser.getUsername())),
                hasProperty("password", equalTo(testUser.getPassword()))
        ));
    }

    @Test
    public void createUserUserAlreadyExistsTest() {
        UserDto testUserDto = TestDataUserUtil.createUserDto();
        when(userRepository.existsByUsername(USERNAME)).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> userService.createUser(testUserDto));
    }

    @Test
    public void updateUserTest() {
        UserDto testUserDto = TestDataUserUtil.createUserDto();
        User testUser = TestDataUserUtil.createUser();
        when(userRepository.findByUsername(testUserDto.getUsername())).thenReturn(Optional.of(testUser));
        when(userRepository.save(any())).thenReturn(testUser);

        UserDto userDto = userService.updateUser(testUser.getUsername(), testUserDto);

        assertThat(userDto, allOf(
                hasProperty("role", equalTo(testUser.getRole())),
                hasProperty("username", equalTo(testUser.getUsername())),
                hasProperty("password", equalTo(testUser.getPassword()))
        ));
    }

    @Test
    public void updateUserUserNotFoundTest() {
        UserDto testUserDto = TestDataUserUtil.createUserDto();
        when(userRepository.findByUsername(USERNAME)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> userService.updateUser(testUserDto.getUsername(), testUserDto));
    }

    @Test
    void deleteUserTest() {
        User testUser = TestDataUserUtil.createUser();
        when(userRepository.findByUsername(USERNAME)).thenReturn(Optional.of(testUser));

        userService.deleteUser(testUser.getUsername());

        verify(userRepository, times(1)).delete(testUser);
    }

    @Test
    void deleteUserUserNotFoundTest() {
        when(userRepository.findByUsername(USERNAME)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(USERNAME));
    }

    @Test
    void getAllUsersTest() {
        User user = TestDataUserUtil.createUser();
        when(userRepository.findAll()).thenReturn(List.of(user));
        assertThat(userService.getAll(), contains(mappingService.mapUserToUserDto(user)));
    }

}
