package com.epam.springcashmachine.repository.impl;

import com.epam.springcashmachine.dto.ProductDto;
import com.epam.springcashmachine.model.Product;
import com.epam.springcashmachine.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final List<Product> products = new ArrayList<>();

    @Override
    public Product getProductByName(String name) {
        return products.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product is not found!"));
    }

    @Override
    public Product getProductByCode(Integer code) {
        return products.stream()
                .filter(product -> product.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product is not found!"));
    }

    public Product createProduct(Product product) {
        products.add(product);
        return product;
    }

    public Product updateProduct(Product product) {
        boolean isDeleted = products.removeIf(p -> p.getName().equals(product.getName()));
        if (isDeleted) {
            products.add(product);
            return product;
        }
        throw new RuntimeException("Product is not found!");
    }

    public void deleteProduct(String name) {
        products.removeIf(product -> product.getName().equals(name));
    }

    @Override
    public List<Product> getAll() {
        return products;
    }
}
