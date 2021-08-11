package com.epam.springcashmachine.service.impl;

import com.epam.springcashmachine.exception.ProductAlreadyExistsException;
import com.epam.springcashmachine.exception.ProductNotFoundException;
import com.epam.springcashmachine.model.Product;
import com.epam.springcashmachine.repository.ProductRepository;
import com.epam.springcashmachine.service.MappingService;
import com.epam.springcashmachine.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Product createProduct(Product product) {
        if (productRepository.getProductByName(product.getName()).isPresent()) {
            throw new ProductAlreadyExistsException();
        }
        product = productRepository.save(product);
        return product;
    }

    @Override
    @Transactional
    public Product getProductByName(String name) {
        log.info("getProduct by name {}", name);
        return productRepository.getProductByName(name).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    @Transactional
    public Product getProductById(Long id) {
        log.info("getProduct by id {}", id);
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    @Transactional
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Product updateProduct(String name, Product product) {
        Product persistedProduct = productRepository.getProductByName(name).orElseThrow(ProductNotFoundException::new);
        Product storedProduct = productRepository.save(product);
        log.info("quantity of product with name {} was successfully updated", storedProduct.getName());
        return storedProduct;
    }

    @Override
    @Transactional
    public void deleteProduct(String name) {
        log.info("deleteProduct by name {}", name);
        Product product = productRepository.getProductByName(name).orElseThrow(ProductNotFoundException::new);
        productRepository.delete(product);
    }


}
