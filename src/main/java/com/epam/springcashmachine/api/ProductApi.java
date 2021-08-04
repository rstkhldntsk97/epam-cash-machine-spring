package com.epam.springcashmachine.api;

import com.epam.springcashmachine.controller.model.ProductModel;
import com.epam.springcashmachine.dto.ProductDto;
import com.epam.springcashmachine.dto.group.OnCreate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Product management API")
@RequestMapping("/api/v1/products")
public interface ProductApi {

    @ApiImplicitParams({@ApiImplicitParam(name = "name", paramType = "path", required = true, value = "Product name")})
    @ApiOperation("Get product by name")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getProductByName/{name}")
    ProductModel getProductByName(@PathVariable String name);

    @ApiImplicitParams({@ApiImplicitParam(name = "code", paramType = "path", required = true, value = "Product code")})
    @ApiOperation("Get product by code")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getProductByCode/{code}")
    ProductModel getProductByCode(@PathVariable Integer code);

    @ApiOperation("Create product")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/createProduct")
//    ProductModel createProduct(@Validated(OnCreate.class) @RequestBody ProductDto productDto);
    ProductModel createProduct(@Valid @RequestBody ProductDto productDto);


    @ApiOperation("Update product")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/updateProduct/")
    ProductModel updateProduct(@RequestBody @Valid ProductDto productDto);

    @ApiOperation("Delete product")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/delete/{name}")
    ResponseEntity<Void> deleteProduct(@PathVariable String name);

    @ApiOperation("Get all products")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    List<ProductModel> getAll();

}
