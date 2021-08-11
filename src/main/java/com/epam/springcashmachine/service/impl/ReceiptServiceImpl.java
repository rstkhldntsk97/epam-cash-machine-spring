package com.epam.springcashmachine.service.impl;

import com.epam.springcashmachine.dto.ProductInReceiptDto;
import com.epam.springcashmachine.exception.ReceiptNotExistsException;
import com.epam.springcashmachine.exception.ReceiptUpdateException;
import com.epam.springcashmachine.model.Product;
import com.epam.springcashmachine.model.Receipt;
import com.epam.springcashmachine.model.enums.Status;
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
    private final ProductService productService;

    @Override
    @Transactional
    public Receipt createReceipt(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    @Override
    @Transactional
    public Receipt getById(Long id) {
        return receiptRepository.findById(id).orElseThrow(ReceiptNotExistsException::new);
    }

    @Override
    @Transactional
    public Receipt updateReceipt(Long id, Receipt receipt) {
        Receipt updatedReceipt = null;
        if (receiptRepository.findById(id).isPresent()) {
            updatedReceipt = receiptRepository.save(receipt);
        }
        log.info("Receipt with id {} was successfully updated", id);
        return updatedReceipt;
    }

    @Override
    @Transactional
    public void deleteReceipt(Long id) {
        Receipt receipt = receiptRepository.findById(id).orElseThrow(ReceiptNotExistsException::new);
        receipt.setStatus(Status.valueOf("ARCHIVED"));
        receiptRepository.save(receipt);
    }


    /**
     * Method adds a new product to receipt and updates product quantity at stock.
     *
     * @param productInReceiptDto desired product to add
     * @param receiptId           receipt
     * @return receiptDto
     */
    @Override
    @Transactional
    public Receipt addProductToReceipt(ProductInReceiptDto productInReceiptDto, Long receiptId) {
        Receipt persistedReceipt = receiptRepository.findById(receiptId).orElseThrow(ReceiptNotExistsException::new);
        Product product = productService.getProductById(productInReceiptDto.getProductId());

        if (!persistedReceipt.getStatus().equals(Status.CLOSED)) {
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
        productService.updateProduct(product.getName(), product);
        log.info("to receipt with id {} added new products", persistedReceipt.getId());
        return updateReceipt(persistedReceipt.getId(), persistedReceipt);
    }

    @Transactional
    @Override
    public Receipt deleteProductFromReceipt(Long productId, Long receiptId) {
        // TODO: solve problem with deleting product from receipt (current implementation doesn't work as expected)
//        Receipt persistedReceipt = receiptRepository.getById(receiptId);;
//        Product product = productRepository.getById(productId);
//        if (!persistedReceipt.getStatus().equals(Status.NEW)) {
//            log.info("Receipt is closed 4ever");
//            throw new ReceiptUpdateException();
//        }
//        if (!persistedReceipt.getProducts().containsKey(product)) {
//            log.info("Receipt doesn't contain this product");
//            throw new ReceiptUpdateException("Receipt doesn't contain this product");
//        }
//        product.setQuantity(product.getQuantity() + persistedReceipt.getProducts().get(product));
//        productService.updateProduct(product.getName(), mappingService.mapProductToProductDto(product));
//        log.info("product was returned");
//        persistedReceipt.getProducts().remove(product);
//        persistedReceipt.setTotal(countTotalForReceipt(persistedReceipt));
//        log.info("product successfully deleted from receipt");
//        persistedReceipt = receiptRepository.save(persistedReceipt);
//        return mappingService.mapReceiptToReceiptDro(persistedReceipt);
        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional
    public List<Receipt> getAll() {
        return receiptRepository.findAll();
    }

    public Long countTotalForReceipt(Receipt receipt) {
        return receipt.getProducts()
                .entrySet()
                .stream()
                .mapToLong(p -> p.getKey().getPrice() * p.getValue())
                .sum();
    }

}
