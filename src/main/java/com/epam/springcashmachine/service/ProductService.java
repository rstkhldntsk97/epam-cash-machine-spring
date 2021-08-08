package com.epam.springcashmachine.service;

import com.epam.springcashmachine.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto);

    ProductDto getProductByName(String name);

    ProductDto getProductById(Long id);

    List<ProductDto> getAll();

    ProductDto updateProduct(String name, ProductDto productDto);

    void deleteProduct(String name);

}
