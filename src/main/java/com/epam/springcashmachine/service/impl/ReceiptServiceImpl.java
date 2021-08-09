package com.epam.springcashmachine.service.impl;

import com.epam.springcashmachine.dto.ProductInReceiptDto;
import com.epam.springcashmachine.dto.ReceiptDto;
import com.epam.springcashmachine.exception.ReceiptUpdateException;
import com.epam.springcashmachine.model.Product;
import com.epam.springcashmachine.model.Receipt;
import com.epam.springcashmachine.model.enums.Status;
import com.epam.springcashmachine.repository.ProductRepository;
import com.epam.springcashmachine.repository.ReceiptRepository;
import com.epam.springcashmachine.service.MappingService;
import com.epam.springcashmachine.service.ProductService;
import com.epam.springcashmachine.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final MappingService mappingService;
    private final ProductService productService;
    private final ProductRepository productRepository;


    @Override
    public ReceiptDto createReceipt(ReceiptDto receiptDto) {
        Receipt receipt = mappingService.mapReceiptDtoToReceipt(receiptDto);
        receipt = receiptRepository.save(receipt);
        return mappingService.mapReceiptToReceiptDro(receipt);
    }

    @Override
    public ReceiptDto getById(Long id) {
        return mappingService.mapReceiptToReceiptDro(receiptRepository.getById(id));
    }

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
        receiptRepository.save(persistedReceipt);
        log.info("Receipt with id {} was successfully updated", id);
        return mappingService.mapReceiptToReceiptDro(persistedReceipt);
    }

    @Override
    public void deleteReceipt(Long id) {
        Receipt receipt = receiptRepository.getById(id);
        receipt.setStatus(Status.valueOf("ARCHIVED"));
        receiptRepository.save(receipt);
    }


    /**
     * Method adds a new product to receipt and updates product quantity at stock.
     *
     * @param productInReceiptDto desired product to add
     * @param receiptId receipt
     * @return receiptDto
     */
    @Override
    @Transactional
    public ReceiptDto addProductToReceipt(ProductInReceiptDto productInReceiptDto,  Long receiptId) {
        Receipt persistedReceipt = receiptRepository.getById(receiptId);
        Long productId = productInReceiptDto.getProductId();
        Product product = productRepository.getById(productId);
        if ( !persistedReceipt.getStatus().equals(Status.NEW)) {
            log.info("Receipt is closed 4ever");
            throw new ReceiptUpdateException();
        }
        if (persistedReceipt.getProducts().containsKey(product)) {
            log.info("product is already in receipt");
            throw new ReceiptUpdateException("product is already in receipt");
        }
        persistedReceipt.getProducts().put(product, productInReceiptDto.getAmount());
        persistedReceipt.setTotal(countTotalForReceipt(persistedReceipt));
        product.setQuantity(product.getQuantity() - productInReceiptDto.getAmount());
        productService.updateProduct(product.getName(),mappingService.mapProductToProductDto(product));
        log.info("to receipt with id {} added new products", persistedReceipt.getId());
        return updateReceipt(persistedReceipt.getId(), mappingService.mapReceiptToReceiptDro(persistedReceipt));
    }

    public Long countTotalForReceipt(Receipt receipt) {
        return receipt.getProducts()
                .entrySet()
                .stream()
                .mapToLong(p -> p.getKey().getPrice() * p.getValue())
                .sum();
    }

}
