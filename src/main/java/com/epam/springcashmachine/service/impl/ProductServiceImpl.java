package com.epam.springcashmachine.service.impl;

import com.epam.springcashmachine.dto.ProductDto;
import com.epam.springcashmachine.model.Product;
import com.epam.springcashmachine.repository.ProductRepository;
import com.epam.springcashmachine.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final PropertyUtilsBean PUB;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = mapProductDtoToProduct(productDto);
        product = productRepository.save(product);
        return mapProductToProductDto(product);
    }

    @Override
    public ProductDto getProductByName(String name) {
        log.info("getProduct by name {}", name);
        Product product = productRepository.getProductByName(name);
        return mapProductToProductDto(product);
    }

    @Override
    public ProductDto getProductById(Long id) {
        log.info("getProduct by id {}", id);
        Product product = productRepository.getById(id);
        return mapProductToProductDto(product);
    }

    @Override
    public List<ProductDto> getAll() {
        List<ProductDto> products = new ArrayList<>();
        List<Product> productsRep = productRepository.findAll();
        for (Product p:productsRep) {
            products.add(mapProductToProductDto(p));
        }
        return products;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        log.info("product quantity by name {} was successfully updated", productDto.getName());
        Product product = mapProductDtoToProduct(productDto);
        product = productRepository.save(product);
        return mapProductToProductDto(product);
    }

    @Override
    public void deleteProduct(String name) {
        log.info("deleteProduct by name {}" , name);
        Product product = productRepository.getProductByName(name);
        productRepository.delete(product);
    }

    @SneakyThrows
    private Product mapProductDtoToProduct(ProductDto productDto) {
        Product product = new Product();
        PUB.copyProperties(product, productDto);
        return product;
    }

    @SneakyThrows
    private ProductDto mapProductToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        PUB.copyProperties(productDto, product);
        return productDto;
    }

}
