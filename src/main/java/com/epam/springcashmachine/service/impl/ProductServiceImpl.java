package com.epam.springcashmachine.service.impl;

import com.epam.springcashmachine.dto.ProductDto;
import com.epam.springcashmachine.exception.ProductAlreadyExistsException;
import com.epam.springcashmachine.exception.ProductNotFoundException;
import com.epam.springcashmachine.model.Product;
import com.epam.springcashmachine.repository.ProductRepository;
import com.epam.springcashmachine.service.MappingService;
import com.epam.springcashmachine.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final MappingService mappingService;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        if (productRepository.getProductByName(productDto.getName()).isPresent()) {
            throw new ProductAlreadyExistsException();
        }
        Product product = mappingService.mapProductDtoToProduct(productDto);
        product = productRepository.save(product);
        return mappingService.mapProductToProductDto(product);
    }

    @Override
    public ProductDto getProductByName(String name) {
        log.info("getProduct by name {}", name);
        Product product = productRepository.getProductByName(name).orElseThrow(ProductNotFoundException::new);
        return mappingService.mapProductToProductDto(product);
    }

    @Override
    public ProductDto getProductById(Long id) {
        log.info("getProduct by id {}", id);
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        return mappingService.mapProductToProductDto(product);
    }

    @Override
    public List<ProductDto> getAll() {
        List<ProductDto> products = new ArrayList<>();
        List<Product> productsRep = productRepository.findAll();
        for (Product p:productsRep) {
            products.add(mappingService.mapProductToProductDto(p));
        }
        return products;
    }

    @Override
    public ProductDto updateProduct(String name, ProductDto productDto) {
        Product persistedProduct = productRepository.getProductByName(name).orElseThrow(ProductNotFoundException::new);
        persistedProduct = mappingService.populateProductWithPresentProductDtoFields(persistedProduct, productDto);
        Product storedProduct = productRepository.save(persistedProduct);
        log.info("quantity of product with name {} was successfully updated", storedProduct.getName());
        return mappingService.mapProductToProductDto(persistedProduct);
    }

    @Override
    public void deleteProduct(String name) {
        log.info("deleteProduct by name {}" , name);
        Product product = productRepository.getProductByName(name).orElseThrow(ProductNotFoundException::new);
        productRepository.delete(product);
    }



}
