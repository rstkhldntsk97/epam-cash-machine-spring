package com.epam.springcashmachine.dto;

import com.epam.springcashmachine.dto.group.OnCreate;
import com.epam.springcashmachine.dto.group.OnUpdate;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

    @Null(message = "'name' should be absent in request", groups = OnUpdate.class)
    @NotBlank(message = "'product name' shouldn't be empty", groups = OnCreate.class)
    private String name;

    @Min(0)
    @Max(9999)
    @Null(message = "'price' should be absent in request", groups = OnUpdate.class)
    @NotBlank(message = "'price' shouldn't be empty", groups = OnCreate.class)
    private Long price;

    @Min(0)
    @Max(9999)
    @NotBlank(message = "'quantity' shouldn't be empty", groups = OnCreate.class)
    private Long quantity;

}
