package com.epam.springcashmachine.service;

import com.epam.springcashmachine.dto.ProductInReceiptDto;
import com.epam.springcashmachine.model.Receipt;

import java.util.List;

public interface ReceiptService{

    Receipt createReceipt(Receipt receipt);

    Receipt getById(Long id);

    List<Receipt> getAll();

    Receipt updateReceipt(Long id, Receipt receipt);

    void deleteReceipt(Long id);

    Receipt addProductToReceipt(ProductInReceiptDto productInReceiptDto, Long receiptId);

    Receipt deleteProductFromReceipt(Long productId, Long receiptId);


}
