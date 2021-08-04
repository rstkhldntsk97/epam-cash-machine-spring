package com.epam.springcashmachine.controller.assembler;

import com.epam.springcashmachine.controller.ProductController;
import com.epam.springcashmachine.controller.model.ProductModel;
import com.epam.springcashmachine.dto.ProductDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductAssembler extends RepresentationModelAssemblerSupport<ProductDto, ProductModel> {

    public static final String GET_BY_CODE = "get_product_by_code";
    public static final String GET_BY_NAME = "get_product_by_name";
    public static final String CREATE_PRODUCT = "create_product";
    public static final String UPDATE_PRODUCT = "update_product";
    public static final String DELETE_PRODUCT = "delete_product";
    public static final String GET_ALL_PRODUCTS = "get_all_products";

    public ProductAssembler() {
        super(ProductDto.class, ProductModel.class);
    }

    @Override
    public ProductModel toModel(ProductDto entity) {
        ProductModel productModel = new ProductModel(entity);

        Link getByCode = linkTo(methodOn(ProductController.class).getProductByCode(entity.getCode())).withRel(GET_BY_CODE);
        Link getByName = linkTo(methodOn(ProductController.class).getProductByName(entity.getName())).withRel(GET_BY_NAME);
        Link create = linkTo(methodOn(ProductController.class).createProduct(entity)).withRel(CREATE_PRODUCT);
        Link update = linkTo(methodOn(ProductController.class).updateProduct(entity)).withRel(UPDATE_PRODUCT);
        Link delete = linkTo(methodOn(ProductController.class).deleteProduct(entity.getName())).withRel(DELETE_PRODUCT);
        Link getAll = linkTo(methodOn(ProductController.class).getAll()).withRel(GET_ALL_PRODUCTS);

        productModel.add(getByCode, getByName, create, update, delete, getAll);

        return productModel;
    }

}
