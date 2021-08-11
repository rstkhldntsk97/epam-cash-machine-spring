package com.epam.springcashmachine.dto;

import com.epam.springcashmachine.dto.group.OnCreate;
import com.epam.springcashmachine.dto.group.OnUpdate;
import com.epam.springcashmachine.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

//    @NotBlank(message = "'user role' shouldn't be empty", groups = OnCreate.class)
    private Role role;

    @NotBlank(message = "'username' shouldn't be empty", groups = OnCreate.class)
    private String username;

    @Null(message = "'password' should be absent in request", groups = OnUpdate.class)
    @NotBlank(message = "'password' shouldn't be empty", groups = OnCreate.class)
    private String password;

}
