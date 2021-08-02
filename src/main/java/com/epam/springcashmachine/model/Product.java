package com.epam.springcashmachine.model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Product {

    private String name;
    private Integer code;
    private Integer price;
    private Integer quantity;

}
