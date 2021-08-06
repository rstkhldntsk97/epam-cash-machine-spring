package com.epam.springcashmachine.controller;

import com.epam.springcashmachine.api.ReceiptApi;
import com.epam.springcashmachine.controller.assembler.ReceiptAssembler;
import com.epam.springcashmachine.controller.model.ReceiptModel;
import com.epam.springcashmachine.dto.ReceiptDto;
import com.epam.springcashmachine.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReceiptController implements ReceiptApi {

//    private final ReceiptService receiptService;
    private final ReceiptAssembler receiptAssembler;

    @Override
    public ReceiptModel createReceipt(ReceiptDto receiptDto) {
        return null;
    }

    @Override
    public ReceiptModel updateReceipt(ReceiptDto receiptDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteReceipt(Integer id) {
        return null;
    }

    @Override
    public List<ReceiptModel> getAllReceipts() {
        return null;
    }

    @Override
    public ReceiptModel addProductToReceipt(Integer productCode, Integer quantity, ReceiptDto receiptDto) {
        return null;
    }

    @Override
    public ReceiptModel deleteProductFromReceipt(Integer productCode, ReceiptDto receiptDto) {
        return null;
    }

}
