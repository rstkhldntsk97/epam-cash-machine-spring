package com.epam.springcashmachine.dto;

import com.epam.springcashmachine.model.Product;
import com.epam.springcashmachine.model.User;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
@Builder
public class ReceiptDto {

    private Integer total;
    private User user;
    private String status;
    private Date createdAt;
    private Map<Product, Integer> products;

}
