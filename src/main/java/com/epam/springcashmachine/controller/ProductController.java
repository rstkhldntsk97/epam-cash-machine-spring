package com.epam.springcashmachine.controller;

import com.epam.springcashmachine.api.ProductApi;
import com.epam.springcashmachine.controller.assembler.ProductAssembler;
import com.epam.springcashmachine.controller.model.ProductModel;
import com.epam.springcashmachine.dto.ProductDto;
import com.epam.springcashmachine.dto.group.OnCreate;
import com.epam.springcashmachine.dto.group.OnUpdate;
import com.epam.springcashmachine.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
        ProductDto productDto = productService.getProductByName(name);
        return productAssembler.toModel(productDto);
    }

    @Override
    public ProductModel getProductByCode(Integer code) {
        ProductDto productDto = productService.getProductByCode(code);
        return productAssembler.toModel(productDto);
    }

    @Override
    public ProductModel createProduct(ProductDto productDto) {
        ProductDto outProductDto = productService.createProduct(productDto);
        return productAssembler.toModel(outProductDto);
    }

    @Override
    public ProductModel updateProduct(@RequestBody @Validated(OnUpdate.class) ProductDto productDto){
        ProductDto outProductDto = productService.updateProduct(productDto);
        return productAssembler.toModel(outProductDto);
    }

    @Override
    public ResponseEntity<Void> deleteProduct(@PathVariable String name){
        productService.deleteProduct(name);
        return ResponseEntity.noContent().build();
    }

    @Override
    public List<ProductModel> getAll(){
        List<ProductDto> productsDto =  productService.getAll();
        List<ProductModel> products = new ArrayList<>();
        for (ProductDto p : productsDto) {
            products.add(productAssembler.toModel(p));
        }
        return products;
    }

}
