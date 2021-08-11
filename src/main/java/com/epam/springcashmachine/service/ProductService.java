package com.epam.springcashmachine.service;

import com.epam.springcashmachine.model.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    Product getProductByName(String name);

    Product getProductById(Long id);

    List<Product> getAll();

    Product updateProduct(String name, Product product);

    void deleteProduct(String name);

}
