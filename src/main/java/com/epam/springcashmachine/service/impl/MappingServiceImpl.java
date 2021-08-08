package com.epam.springcashmachine.service.impl;

import com.epam.springcashmachine.dto.ProductDto;
import com.epam.springcashmachine.dto.ReceiptDto;
import com.epam.springcashmachine.dto.UserDto;
import com.epam.springcashmachine.model.Product;
import com.epam.springcashmachine.model.Receipt;
import com.epam.springcashmachine.model.User;
import com.epam.springcashmachine.model.enums.Role;
import com.epam.springcashmachine.service.MappingService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class MappingServiceImpl implements MappingService {

    private final PropertyUtilsBean PUB;

    @SneakyThrows
    public UserDto mapUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        PUB.copyProperties(userDto, user);
        return userDto;
    }

    @SneakyThrows
    public User mapUserDtoToUser(UserDto userDto) {
        User user = new User();
        PUB.copyProperties(user, userDto);
        return user;
    }

    @Override
    public User populateUserWithPresentUserDtoFields(User user, UserDto userDto) {
        Role role = userDto.getRole();
        if (Objects.nonNull(role)) {
            user.setRole(role);
        }
        if (Objects.nonNull(userDto.getUsername())) {
            user.setUsername(userDto.getUsername());
        }
        return user;
    }

    @Override
    @SneakyThrows
    public Product mapProductDtoToProduct(ProductDto productDto) {
        Product product = new Product();
        PUB.copyProperties(product, productDto);
        return product;
    }

    @Override
    @SneakyThrows
    public ProductDto mapProductToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        PUB.copyProperties(productDto, product);
        return productDto;
    }

    @Override
    public Product populateProductWithPresentProductDtoFields(Product product, ProductDto productDto) {
        if (Objects.nonNull(productDto.getQuantity())){
            product.setQuantity(productDto.getQuantity());
        }
        return product; 
    }

    @SneakyThrows
    @Override
    public ReceiptDto mapReceiptToReceiptDro(Receipt receipt) {
        ReceiptDto receiptDto = new ReceiptDto();
        PUB.copyProperties(receiptDto, receipt);
        return receiptDto;
    }

    @Override
    public Receipt populateReceiptWithPresentReceiptFields(Receipt receipt, ReceiptDto receiptDto) {
        if (Objects.nonNull(receiptDto.getTotal())){
            receipt.setTotal(receiptDto.getTotal());
        }
        if (Objects.nonNull(receiptDto.getStatus())){
            receipt.setStatus(receiptDto.getStatus());
        }
        return receipt;
    }

    @SneakyThrows
    @Override
    public Receipt mapReceiptDtoToReceipt(ReceiptDto receiptDto) {
        Receipt receipt = new Receipt();
        PUB.copyProperties(receipt, receiptDto);
        return receipt;
    }

}
