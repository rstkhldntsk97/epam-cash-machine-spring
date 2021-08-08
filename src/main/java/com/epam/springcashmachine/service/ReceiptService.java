package com.epam.springcashmachine.service;

import com.epam.springcashmachine.dto.ReceiptDto;

import java.util.List;

public interface ReceiptService{

    List<ReceiptDto> getAll();

    ReceiptDto updateReceipt(Long id, ReceiptDto receiptDto);

    ReceiptDto addProductToReceipt(Long productId, Long quantity, ReceiptDto receiptDto);

}
