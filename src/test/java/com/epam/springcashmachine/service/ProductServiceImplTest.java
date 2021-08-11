package com.epam.springcashmachine.service;

import com.epam.springcashmachine.dto.ProductDto;
import com.epam.springcashmachine.exception.ProductAlreadyExistsException;
import com.epam.springcashmachine.exception.ProductNotFoundException;
import com.epam.springcashmachine.exception.UserNotFoundException;
import com.epam.springcashmachine.model.Product;
import com.epam.springcashmachine.repository.ProductRepository;
import com.epam.springcashmachine.service.impl.MappingServiceImpl;
import com.epam.springcashmachine.service.impl.ProductServiceImpl;
import com.epam.springcashmachine.test.util.TestDataUtil;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.epam.springcashmachine.test.util.TestDataUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Spy
    private final MappingService mappingService = new MappingServiceImpl(new PropertyUtilsBean());

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductServiceImpl service;

    @Test
    void getProductByNameTest(){
        Product product = TestDataUtil.createProduct();
        when(repository.getProductByName(PRODUCT_NAME)).thenReturn(Optional.of(product));

        ProductDto productDto = service.getProductByName(PRODUCT_NAME);

        assertThat(productDto, allOf(
                hasProperty("price", equalTo(product.getPrice())),
                hasProperty("quantity", equalTo(product.getQuantity()))
        ));
    }

    @Test
    void getProductByNameProductNotFoundTest(){
        when(repository.getProductByName(PRODUCT_NAME)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> service.getProductByName(PRODUCT_NAME));
    }

    @Test
    void getProductByIdTest(){
        Product product = TestDataUtil.createProduct();
        when(repository.findById(PRODUCT_ID)).thenReturn(Optional.of(product));
        ProductDto productDto = service.getProductById(PRODUCT_ID);

        assertThat(productDto, allOf(
                hasProperty("id", equalTo(product.getId())),
                hasProperty("price", equalTo(product.getPrice())),
                hasProperty("quantity", equalTo(product.getQuantity()))
        ));
    }

    @Test
    void getProductByIdProductNotFoundTest(){
        when(repository.findById(PRODUCT_ID)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> service.getProductById(PRODUCT_ID));
    }

    @Test
    void createProductTest(){
        Product product = TestDataUtil.createProduct();
        ProductDto testProductDto = TestDataUtil.createProductDto();
        when(repository.save(any())).thenReturn(product);
        when(mappingService.mapProductToProductDto(product)).thenReturn(testProductDto);

        ProductDto productDto = service.createProduct(testProductDto);

        assertThat(productDto, allOf(
                hasProperty("id", equalTo(product.getId())),
                hasProperty("price", equalTo(product.getPrice())),
                hasProperty("quantity", equalTo(product.getQuantity())),
                hasProperty("name", equalTo(product.getName()))
        ));
    }

    @Test
    void createProductProductAlreadyExistsTest(){
        ProductDto testProductDto = TestDataUtil.createProductDto();
        when(repository.getProductByName(PRODUCT_NAME)).thenReturn(Optional.of(TestDataUtil.createProduct()));
        assertThrows(ProductAlreadyExistsException.class, () -> service.createProduct(testProductDto));
    }

    @Test
    void getAllTest() {
        Product product = TestDataUtil.createProduct();
        when(repository.findAll()).thenReturn(List.of(product));
        assertThat(service.getAll(), contains(mappingService.mapProductToProductDto(product)));
    }



}
