package com.epam.springcashmachine.service;

import com.epam.springcashmachine.dto.ProductInReceiptDto;
import com.epam.springcashmachine.exception.ProductNotFoundException;
import com.epam.springcashmachine.exception.ReceiptNotExistsException;
import com.epam.springcashmachine.model.Product;
import com.epam.springcashmachine.model.Receipt;
import com.epam.springcashmachine.repository.ProductRepository;
import com.epam.springcashmachine.repository.ReceiptRepository;
import com.epam.springcashmachine.service.impl.MappingServiceImpl;
import com.epam.springcashmachine.service.impl.ProductServiceImpl;
import com.epam.springcashmachine.service.impl.ReceiptServiceImpl;
import com.epam.springcashmachine.test.util.TestDataProductUtil;
import com.epam.springcashmachine.test.util.TestDataReceiptUtil;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.epam.springcashmachine.test.util.TestDataReceiptUtil.*;
import static org.mockito.Mockito.when;
import static com.epam.springcashmachine.test.util.TestDataProductUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReceiptServiceImplTest {

    @InjectMocks
    private ReceiptServiceImpl service;

    @Mock
    private ReceiptRepository repository;

    @Mock
    private ProductService productService;

    @Test
    void getByIdTest() {
        Receipt receipt = TestDataReceiptUtil.createReceipt();
        when(repository.findById(RECEIPT_ID)).thenReturn(Optional.of(receipt));
        Receipt persistedReceipt = service.getById(RECEIPT_ID);

        assertThat(persistedReceipt, allOf(
                hasProperty("id", equalTo(receipt.getId())),
                hasProperty("total", equalTo(receipt.getTotal())),
                hasProperty("user", equalTo(receipt.getUser())),
                hasProperty("status", equalTo(receipt.getStatus())),
                hasProperty("products", equalTo(receipt.getProducts()))
        ));
    }

    @Test
    void getByIdReceiptNotExistsTest() {
        when(repository.findById(RECEIPT_ID)).thenReturn(Optional.empty());
        assertThrows(ReceiptNotExistsException.class, () -> service.getById(RECEIPT_ID));
    }

    @Test
    void createReceiptTest() {
        Receipt receipt = TestDataReceiptUtil.createReceipt();
        when(repository.save(any())).thenReturn(receipt);

        Receipt storedReceipt = service.createReceipt(receipt);

        assertThat(storedReceipt, allOf(
                hasProperty("id", equalTo(receipt.getId())),
                hasProperty("total", equalTo(receipt.getTotal())),
                hasProperty("user", equalTo(receipt.getUser())),
                hasProperty("status", equalTo(receipt.getStatus())),
                hasProperty("products", equalTo(receipt.getProducts()))
        ));
    }

    @Test
    void updateReceiptTest() {
        Receipt receipt = TestDataReceiptUtil.createReceipt();
        when(repository.findById(RECEIPT_ID)).thenReturn(Optional.of(receipt));
        when(repository.save(any())).thenReturn(receipt);

        Receipt updatedReceipt = service.updateReceipt(receipt.getId(), receipt);

        assertThat(updatedReceipt, allOf(
                hasProperty("id", equalTo(receipt.getId())),
                hasProperty("total", equalTo(receipt.getTotal())),
                hasProperty("user", equalTo(receipt.getUser())),
                hasProperty("status", equalTo(receipt.getStatus())),
                hasProperty("products", equalTo(receipt.getProducts()))
        ));
    }

    @Test
    void deleteReceiptTest() {
        Receipt receipt = TestDataReceiptUtil.createReceipt();
        when(repository.findById(RECEIPT_ID)).thenReturn(Optional.of(receipt));

        service.deleteReceipt(receipt.getId());

        Receipt deletedReceipt = service.getById(receipt.getId());

        assertThat(deletedReceipt, allOf(
                hasProperty("id", equalTo(receipt.getId())),
                hasProperty("total", equalTo(receipt.getTotal())),
                hasProperty("user", equalTo(receipt.getUser())),
                hasProperty("status", equalTo(receipt.getStatus())),
                hasProperty("products", equalTo(receipt.getProducts()))
        ));
    }

    @Test
    void deleteReceiptReceiptNotExists() {
        when(repository.findById(RECEIPT_ID)).thenReturn(Optional.empty());
        assertThrows(ReceiptNotExistsException.class, () -> service.deleteReceipt(RECEIPT_ID));
    }

//    @Test
//    void addProductToReceiptTest(){
//        Receipt receiptBefore = TestDataReceiptUtil.createReceipt();
//        Receipt receiptAfter = TestDataReceiptUtil.createAnotherReceipt();
//        ProductInReceiptDto productInReceiptDto = TestDataReceiptUtil.createProductInReceiptDto();
//        Product productBeforeUpdate = TestDataReceiptUtil.product;
//        Product productAfterUpdate = TestDataReceiptUtil.product2;
//
//        when(repository.findById(receiptBefore.getId())).thenReturn(Optional.of(receiptBefore));
//
//        when(productService.getProductById(productInReceiptDto.getProductId())).thenReturn(productBeforeUpdate);
//
//        when(service.updateReceipt(receiptBefore.getId(), receiptBefore)).thenReturn(receiptAfter);
//        when(productService.updateProduct(product.getName(), product)).thenReturn(productAfterUpdate);
//
//        Receipt candidateReceipt = service.addProductToReceipt(productInReceiptDto, receiptBefore.getId());
//
//        assertThat(candidateReceipt, allOf(
////                hasProperty("id", equalTo(createAnotherReceipt().getId())),
//                hasProperty("total", equalTo(receiptAfter.getTotal())),
//                hasProperty("user", equalTo(receiptAfter.getUser())),
//                hasProperty("status", equalTo(receiptAfter.getStatus())),
//                hasProperty("products", equalTo(receiptAfter.getProducts()))
//        ));
//    }

    @Test
    void getAllTest() {
        Receipt receipt = TestDataReceiptUtil.createReceipt();
        when(repository.findAll()).thenReturn(List.of(receipt));
        assertThat(service.getAll(), contains(receipt));
    }

}
