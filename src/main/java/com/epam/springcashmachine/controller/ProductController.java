package com.epam.springcashmachine.controller;

import com.epam.springcashmachine.api.ProductApi;
import com.epam.springcashmachine.controller.assembler.ProductAssembler;
import com.epam.springcashmachine.controller.model.ProductModel;
import com.epam.springcashmachine.model.Product;
import com.epam.springcashmachine.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApi {

    private final ProductService productService;
    private final ProductAssembler productAssembler;

    @Override
    public ProductModel getProductByName(String name) {
        Product product = productService.getProductByName(name);
        return productAssembler.toModel(product);
    }

    @Override
    public ProductModel getProductById(Long id) {
        Product product = productService.getProductById(id);
        return productAssembler.toModel(product);
    }

    @Override
    public ProductModel createProduct(Product product) {
        Product outProduct = productService.createProduct(product);
        return productAssembler.toModel(outProduct);
    }

    @Override
    public ProductModel updateProduct(String name, Product product) {
        Product outProduct = productService.updateProduct(name, product);
        return productAssembler.toModel(outProduct);
    }

    @Override
    public ResponseEntity<Void> deleteProduct(String name) {
        productService.deleteProduct(name);
        return ResponseEntity.noContent().build();
    }

    @Override
    public List<ProductModel> getAll() {
        List<Product> products = productService.getAll();
        List<ProductModel> productModels = new ArrayList<>();
        for (Product p : products) {
            productModels.add(productAssembler.toModel(p));
        }
        return productModels;
    }

}
