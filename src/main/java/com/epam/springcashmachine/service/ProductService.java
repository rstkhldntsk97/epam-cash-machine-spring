package com.epam.springcashmachine.service;

import com.epam.springcashmachine.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto);

    ProductDto getProductByName(String name);

    ProductDto getProductByCode(Integer code);

    List<ProductDto> getAll();

    ProductDto updateProduct(ProductDto productDto);

    void deleteProduct(String name);

}
