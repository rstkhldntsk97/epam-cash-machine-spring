package com.epam.springcashmachine.service.impl;

import com.epam.springcashmachine.dto.ProductDto;
import com.epam.springcashmachine.model.Product;
import com.epam.springcashmachine.repository.ProductRepository;
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

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = mapProductDtoToProduct(productDto);
        product = productRepository.createProduct(product);
        return mapProductToProductDto(product);
    }

    @Override
    public ProductDto getProductByName(String name) {
        log.info("getProduct by name {}", name);
        Product product = productRepository.getProductByName(name);
        return mapProductToProductDto(product);
    }

    @Override
    public ProductDto getProductByCode(Integer code) {
        log.info("getProduct by code {}", code);
        Product product = productRepository.getProductByCode(code);
        return mapProductToProductDto(product);
    }

    @Override
    public List<ProductDto> getAll() {
        List<ProductDto> products = new ArrayList<>();
        List<Product> productsRep = productRepository.getAll();
        for (Product p:productsRep) {
            products.add(mapProductToProductDto(p));
        }
        return products;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        log.info("product quantity by name {} was successfully updated", productDto.getName());
        Product product = mapProductDtoToProduct(productDto);
        product = productRepository.updateProduct(product);
        return mapProductToProductDto(product);
    }

    @Override
    public void deleteProduct(String name) {
        log.info("deleteProduct by name {}" , name);
        productRepository.deleteProduct(name);
    }

    private Product mapProductDtoToProduct(ProductDto productDto) {
        return Product.builder()
                .name(productDto.getName())
                .code(productDto.getCode())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .build();
    }

    private ProductDto mapProductToProductDto(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .code(product.getCode())
                .quantity(product.getQuantity())
                .build();
    }

}
