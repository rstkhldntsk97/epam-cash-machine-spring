package com.epam.springcashmachine.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {

    private String name;

    @Min(0)
    @Max(9999)
    private Integer code;

    @Min(0)
    @Max(9999)
    private Integer price;

    @Min(0)
    @Max(9999)
    private Integer quantity;

}
