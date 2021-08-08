package com.epam.springcashmachine.service;

import com.epam.springcashmachine.dto.ProductDto;
import com.epam.springcashmachine.dto.ReceiptDto;
import com.epam.springcashmachine.dto.UserDto;
import com.epam.springcashmachine.model.Product;
import com.epam.springcashmachine.model.Receipt;
import com.epam.springcashmachine.model.User;
import lombok.SneakyThrows;

public interface MappingService {

    UserDto mapUserToUserDto(User user);

    User mapUserDtoToUser(UserDto userDto);

    User populateUserWithPresentUserDtoFields(User user, UserDto userDto);

    Product mapProductDtoToProduct(ProductDto productDto);

    ProductDto mapProductToProductDto(Product product);

    Product populateProductWithPresentProductDtoFields(Product product, ProductDto productDto);

    ReceiptDto mapReceiptToReceiptDro(Receipt r);

    Receipt populateReceiptWithPresentReceiptFields(Receipt persistedReceipt, ReceiptDto receiptDto);

    Receipt mapReceiptDtoToReceipt(ReceiptDto receiptDto);

}
