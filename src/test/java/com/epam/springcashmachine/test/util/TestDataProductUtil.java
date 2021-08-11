package com.epam.springcashmachine.test.util;

import com.epam.springcashmachine.model.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDataProductUtil {

    public static Long PRODUCT_ID = 1L;
    public static String PRODUCT_NAME = "productNameTest";
    public static Long PRODUCT_PRICE = 100L;
    public static Long PRODUCT_QUANTITY = 10L;

    public static Product createProduct() {
        return Product.builder()
                .id(PRODUCT_ID)
                .name(PRODUCT_NAME)
                .price(PRODUCT_PRICE)
                .quantity(PRODUCT_QUANTITY)
                .build();
    }

}
