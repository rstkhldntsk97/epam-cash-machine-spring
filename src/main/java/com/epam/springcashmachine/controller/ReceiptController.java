package com.epam.springcashmachine.controller;

import com.epam.springcashmachine.api.ReceiptApi;
import com.epam.springcashmachine.controller.assembler.ReceiptAssembler;
import com.epam.springcashmachine.controller.model.ReceiptModel;
import com.epam.springcashmachine.dto.ProductInReceiptDto;
import com.epam.springcashmachine.model.Receipt;
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

    @Override
    public ReceiptModel getReceipt(Long id) {
        return receiptAssembler.toModel(receiptService.getById(id));
    }

    @Override
    public ReceiptModel createReceipt(Receipt receiptDto) {
        return receiptAssembler.toModel(receiptService.createReceipt(receiptDto));
    }

    @Override
    public ReceiptModel updateReceipt(Long id, Receipt receiptDto) {
        Receipt outReceiptDto = receiptService.updateReceipt(id, receiptDto);
        return receiptAssembler.toModel(outReceiptDto);
    }

    @Override
    public ResponseEntity<Void> deleteReceipt(Long id) {
        receiptService.deleteReceipt(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public List<ReceiptModel> getAllReceipts() {
        List<Receipt> outReceipts = receiptService.getAll();
        List<ReceiptModel> receipts = new ArrayList<>();
        for (Receipt receipt : outReceipts) {
            receipts.add(receiptAssembler.toModel(receipt));
        }
        return receipts;
    }

    @Override
    public ReceiptModel addProductToReceipt(Long receiptId, ProductInReceiptDto productInReceiptDto) {
        return receiptAssembler.toModel(receiptService.addProductToReceipt(productInReceiptDto, receiptId));
    }

    @Override
    public ReceiptModel deleteProductFromReceipt(Long receiptId, Long productId) {
        return receiptAssembler.toModel(receiptService.deleteProductFromReceipt(productId, receiptId));
    }

}
