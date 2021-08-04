package com.epam.springcashmachine.controller.model;

import com.epam.springcashmachine.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ProductModel extends RepresentationModel<ProductModel> {

    @JsonUnwrapped
    private ProductDto productDto;

}