package com.epam.springcashmachine.dto;

import com.epam.springcashmachine.dto.group.OnCreate;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {

    @NotBlank(message = "'product name' shouldn't be empty", groups = OnCreate.class)
    private String name;

    @Min(0)
    @Max(9999)
    @NotBlank(message = "'product code' shouldn't be empty", groups = OnCreate.class)
    private Integer code;

    @Min(0)
    @Max(9999)
    @NotBlank(message = "'price' shouldn't be empty", groups = OnCreate.class)
    private Integer price;

    @Min(0)
    @Max(9999)
    @NotBlank(message = "'quantity' shouldn't be empty", groups = OnCreate.class)
    private Integer quantity;

}
