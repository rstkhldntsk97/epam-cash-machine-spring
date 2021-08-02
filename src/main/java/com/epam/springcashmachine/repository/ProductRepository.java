package com.epam.springcashmachine.repository;

import com.epam.springcashmachine.model.Product;

import java.util.List;

public interface ProductRepository {

    Product getProductByName(String name);

    Product getProductByCode(Integer code);

    Product createProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(String name);

    List<Product> getAll();

}
