package com.epam.springcashmachine.controller;

import com.epam.springcashmachine.dto.ProductDto;
import com.epam.springcashmachine.dto.group.OnCreate;
import com.epam.springcashmachine.dto.group.OnUpdate;
import com.epam.springcashmachine.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getProduct/{name}")
    public ProductDto getProductByName(@PathVariable String name) {
        return productService.getProductByName(name);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getProduct/{code}")
    public ProductDto getProductByCode(@PathVariable Integer code) {
        return productService.getProductByCode(code);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/createProduct")
    public ProductDto createProduct(@RequestBody @Validated(OnCreate.class) ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public List<ProductDto> getAll(){
        return productService.getAll();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String name){
        productService.deleteProduct(name);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateProduct")
    public ProductDto updateProduct(@RequestBody @Validated(OnUpdate.class) ProductDto productDto){
        return productService.updateProduct(productDto);
    }

}
