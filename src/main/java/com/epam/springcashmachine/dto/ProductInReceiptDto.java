package com.epam.springcashmachine.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductInReceiptDto {

    Long productId;

    Long amount;

}
