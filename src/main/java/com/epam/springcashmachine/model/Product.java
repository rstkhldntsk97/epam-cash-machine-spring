package com.epam.springcashmachine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String name;
    private Integer code;
    private Integer price;
    private Integer quantity;

}
