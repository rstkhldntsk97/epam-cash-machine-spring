package com.epam.springcashmachine.test.util;

import com.epam.springcashmachine.dto.ProductDto;
import com.epam.springcashmachine.dto.UserDto;
import com.epam.springcashmachine.model.Product;
import com.epam.springcashmachine.model.User;
import com.epam.springcashmachine.model.enums.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDataUtil {

    public static final String USERNAME = "usernameTest";
    public static final Role CASHIER = Role.valueOf("CASHIER");
    private static final String PASSWORD = "password";

    public static final Long PRODUCT_ID = 1L;
    public static final String PRODUCT_NAME = "productNameTest";
    public static final Long PRODUCT_PRICE = 100L;
    public static final Long PRODUCT_QUANTITY = 100L;

    public static User createUser() {
        return User.builder()
                .role(CASHIER)
                .username(USERNAME)
                .password(PASSWORD)
                .build();
    }

    public static UserDto createUserDto() {
        return UserDto.builder()
                .role(CASHIER)
                .username(USERNAME)
                .password(PASSWORD)
                .build();
    }

    public static Product createProduct() {
        return Product.builder()
                .id(PRODUCT_ID)
                .name(PRODUCT_NAME)
                .price(PRODUCT_PRICE)
                .quantity(PRODUCT_QUANTITY)
                .build();
    }

    public static ProductDto createProductDto(){
        return ProductDto.builder()
                .id(PRODUCT_ID)
                .name(PRODUCT_NAME)
                .price(PRODUCT_PRICE)
                .quantity(PRODUCT_QUANTITY)
                .build();
    }

}