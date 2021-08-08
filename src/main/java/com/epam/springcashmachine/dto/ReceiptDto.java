package com.epam.springcashmachine.dto;

import com.epam.springcashmachine.model.Product;
import com.epam.springcashmachine.model.User;
import com.epam.springcashmachine.model.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
public class ReceiptDto {

    private Long id;
    private Integer total;
    private User user;
    private Status status;
    private Map<Product, Integer> products;

}
