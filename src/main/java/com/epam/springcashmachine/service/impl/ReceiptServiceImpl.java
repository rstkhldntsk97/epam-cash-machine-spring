package com.epam.springcashmachine.service.impl;

import com.epam.springcashmachine.dto.ReceiptDto;
import com.epam.springcashmachine.model.Receipt;
import com.epam.springcashmachine.repository.ProductRepository;
import com.epam.springcashmachine.repository.ReceiptRepository;
import com.epam.springcashmachine.service.MappingService;
import com.epam.springcashmachine.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final MappingService mappingService;
    private final ProductRepository productRepository;

    @Override
    public List<ReceiptDto> getAll() {
        List<ReceiptDto> receiptDtos = new ArrayList<>();
        List<Receipt> receiptsRep = receiptRepository.findAll();
        for (Receipt r:receiptsRep) {
            receiptDtos.add(mappingService.mapReceiptToReceiptDro(r));
        }
        return receiptDtos;
    }

    @Override
    public ReceiptDto updateReceipt(Long id, ReceiptDto receiptDto) {
        Receipt persistedReceipt = receiptRepository.getById(id);
        persistedReceipt = mappingService.populateReceiptWithPresentReceiptFields(persistedReceipt, receiptDto);
        Receipt storedReceipt = receiptRepository.save(persistedReceipt);
        return mappingService.mapReceiptToReceiptDro(persistedReceipt);
    }

    @Override
    public ReceiptDto addProductToReceipt(Long productId, Long quantity, ReceiptDto receiptDto) {
//        Product product = productRepository.getById(productId);
//        ProductInReceipt productInReceipt = new ProductInReceipt();
//        productInReceipt.setProduct(product);
//        productInReceipt.setQuantity(quantity);
//        productInReceipt.setReceipt(mappingService.mapReceiptDtoToReceipt(receiptDto));
//        receiptRepository.addProductToReceipt(productInReceipt);
        return null;
    }

}
