package com.epam.springcashmachine.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class Receipt {

    private Integer total;
    private User user;
    private String status;
    private Date createdAt;
    private Map<Product, Integer> products;

}
