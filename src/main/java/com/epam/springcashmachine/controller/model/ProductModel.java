package com.epam.springcashmachine.controller.model;

import com.epam.springcashmachine.model.Product;
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
    private Product product;

}
