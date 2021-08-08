package com.epam.springcashmachine.controller;

import com.epam.springcashmachine.api.ReceiptApi;
import com.epam.springcashmachine.controller.assembler.ReceiptAssembler;
import com.epam.springcashmachine.controller.model.ReceiptModel;
import com.epam.springcashmachine.dto.ReceiptDto;
import com.epam.springcashmachine.service.ProductService;
import com.epam.springcashmachine.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReceiptController implements ReceiptApi {

    private final ReceiptService receiptService;
    private final ReceiptAssembler receiptAssembler;
    private final ProductService productService;

    @Override
    public ReceiptModel createReceipt(ReceiptDto receiptDto) {
        return null;
    }

    @Override
    public ReceiptModel updateReceipt(Long id, ReceiptDto receiptDto) {
        ReceiptDto outReceiptDto = receiptService.updateReceipt(id, receiptDto);
        return receiptAssembler.toModel(outReceiptDto);
    }

    @Override
    public ResponseEntity<Void> deleteReceipt(Integer id) {
        return null;
    }

    @Override
    public List<ReceiptModel> getAllReceipts() {
        List<ReceiptDto> outReceipts = receiptService.getAll();
        List<ReceiptModel> receipts = new ArrayList<>();
        for (ReceiptDto receipt : outReceipts) {
            receipts.add(receiptAssembler.toModel(receipt));
        }
        return receipts;
    }

    @Override
    public ReceiptModel addProductToReceipt(Long productCode, Long quantity, ReceiptDto receiptDto) {
        receiptService.addProductToReceipt(productCode, quantity, receiptDto);
        return null;
    }

    @Override
    public ReceiptModel deleteProductFromReceipt(Integer productCode, ReceiptDto receiptDto) {
        return null;
    }

}
