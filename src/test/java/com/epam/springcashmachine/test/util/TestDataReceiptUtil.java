package com.epam.springcashmachine.test.util;

import com.epam.springcashmachine.dto.ProductInReceiptDto;
import com.epam.springcashmachine.model.Product;
import com.epam.springcashmachine.model.Receipt;
import com.epam.springcashmachine.model.enums.Status;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDataReceiptUtil {

    public static Long RECEIPT_ID = 1L;
    public static Long ANOTHER_RECEIPT_ID = 2L;
    public static Status RECEIPT_STATUS = Status.CLOSED;
    public static Long RECEIPT_TOTAL = 1000L;
    public static Long ANOTHER_RECEIPT_TOTAL = 1500L;
    public static Map<Product, Long> PRODUCTS = new HashMap<>();
    public static Map<Product, Long> ANOTHER_PRODUCTS = new HashMap<>();
    public static Product product = new Product(1L, "p1", 100L, 100L);
    public static Product product2 = new Product(2L, "p2", 100L, 100L);



    public static Receipt createReceipt() {
        PRODUCTS.put(product, 10L);
        return Receipt.builder()
                .id(RECEIPT_ID)
                .status(RECEIPT_STATUS)
                .user(TestDataUserUtil.createUser())
                .total(RECEIPT_TOTAL)
                .products(PRODUCTS)
                .build();
    }

    public static Receipt createAnotherReceipt() {
        ANOTHER_PRODUCTS.put(product, 10L);
        ANOTHER_PRODUCTS.put(product2, 5L);
        return Receipt.builder()
                .id(ANOTHER_RECEIPT_ID)
                .status(RECEIPT_STATUS)
                .user(TestDataUserUtil.createUser())
                .total(ANOTHER_RECEIPT_TOTAL)
                .products(ANOTHER_PRODUCTS)
                .build();
    }

    public static ProductInReceiptDto createProductInReceiptDto() {
        return ProductInReceiptDto.builder()
                .productId(2L)
                .amount(5L)
                .build();
    }

}
