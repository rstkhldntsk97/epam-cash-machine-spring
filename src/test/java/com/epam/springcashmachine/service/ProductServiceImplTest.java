package com.epam.springcashmachine.service;

import com.epam.springcashmachine.exception.ProductAlreadyExistsException;
import com.epam.springcashmachine.exception.ProductNotFoundException;
import com.epam.springcashmachine.model.Product;
import com.epam.springcashmachine.repository.ProductRepository;
import com.epam.springcashmachine.service.impl.ProductServiceImpl;
import com.epam.springcashmachine.test.util.TestDataProductUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.epam.springcashmachine.test.util.TestDataProductUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductServiceImpl service;

    @Test
    void getProductByNameTest() {
        Product product = TestDataProductUtil.createProduct();
        when(repository.getProductByName(PRODUCT_NAME)).thenReturn(Optional.of(product));

        Product persistedProduct = service.getProductByName(PRODUCT_NAME);

        assertThat(persistedProduct, allOf(
                hasProperty("price", equalTo(product.getPrice())),
                hasProperty("quantity", equalTo(product.getQuantity()))
        ));
    }

    @Test
    void getProductByNameProductNotFoundTest() {
        when(repository.getProductByName(PRODUCT_NAME)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> service.getProductByName(PRODUCT_NAME));
    }

    @Test
    void getProductByIdTest() {
        Product product = TestDataProductUtil.createProduct();
        when(repository.findById(PRODUCT_ID)).thenReturn(Optional.of(product));
        Product persistedProduct = service.getProductById(PRODUCT_ID);

        assertThat(persistedProduct, allOf(
                hasProperty("id", equalTo(product.getId())),
                hasProperty("price", equalTo(product.getPrice())),
                hasProperty("quantity", equalTo(product.getQuantity()))
        ));
    }

    @Test
    void getProductByIdProductNotFoundTest() {
        when(repository.findById(PRODUCT_ID)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> service.getProductById(PRODUCT_ID));
    }

    @Test
    void createProductTest() {
        Product product = TestDataProductUtil.createProduct();
        when(repository.save(any())).thenReturn(product);

        Product storedProduct = service.createProduct(product);

        assertThat(storedProduct, allOf(
                hasProperty("id", equalTo(product.getId())),
                hasProperty("price", equalTo(product.getPrice())),
                hasProperty("quantity", equalTo(product.getQuantity())),
                hasProperty("name", equalTo(product.getName()))
        ));
    }

    @Test
    void createProductProductAlreadyExistsTest() {
        Product testProductDto = TestDataProductUtil.createProduct();
        when(repository.getProductByName(PRODUCT_NAME)).thenReturn(Optional.of(TestDataProductUtil.createProduct()));
        assertThrows(ProductAlreadyExistsException.class, () -> service.createProduct(testProductDto));
    }

    @Test
    void updateProductTest() {
        Product testProduct = TestDataProductUtil.createProduct();
        when(repository.getProductByName(testProduct.getName())).thenReturn(Optional.of(testProduct));
        when(repository.save(any())).thenReturn(testProduct);

        Product productDto = service.updateProduct(testProduct.getName(), testProduct);

        assertThat(productDto, allOf(
                hasProperty("id", equalTo(testProduct.getId())),
                hasProperty("price", equalTo(testProduct.getPrice())),
                hasProperty("quantity", equalTo(testProduct.getQuantity())),
                hasProperty("name", equalTo(testProduct.getName()))
        ));
    }

    @Test
    void updateProductProductNotFoundException() {
        Product product = TestDataProductUtil.createProduct();
        when(repository.getProductByName(PRODUCT_NAME)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class,
                () -> service.updateProduct(product.getName(), product));
    }

    @Test
    void deleteProduct() {
        Product testProduct = TestDataProductUtil.createProduct();
        when(repository.getProductByName(PRODUCT_NAME)).thenReturn(Optional.of(testProduct));

        service.deleteProduct(testProduct.getName());

        verify(repository, times(1)).delete(testProduct);
    }

    @Test
    void deleteProductProductNotFound() {
        when(repository.getProductByName(PRODUCT_NAME)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> service.deleteProduct(PRODUCT_NAME));
    }

    @Test
    void getAllTest() {
        Product product = TestDataProductUtil.createProduct();
        when(repository.findAll()).thenReturn(List.of(product));
        assertThat(service.getAll(), contains(product));
    }

}
