package com.epam.springcashmachine.service;

import com.epam.springcashmachine.dto.ProductInReceiptDto;
import com.epam.springcashmachine.dto.ReceiptDto;

import java.util.List;

public interface ReceiptService{

    ReceiptDto createReceipt(ReceiptDto receiptDto);

    ReceiptDto getById(Long id);

    List<ReceiptDto> getAll();

    ReceiptDto updateReceipt(Long id, ReceiptDto receiptDto);

    void deleteReceipt(Long id);

    ReceiptDto addProductToReceipt(ProductInReceiptDto productInReceiptDto, Long receiptId);


}
