package com.epam.springcashmachine.api;

import com.epam.springcashmachine.controller.model.ReceiptModel;
import com.epam.springcashmachine.dto.ProductInReceiptDto;
import com.epam.springcashmachine.dto.ReceiptDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Receipt management API")
@RequestMapping("api/v1/receipts")
public interface ReceiptApi {

    @ApiOperation("Create receipt")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/createReceipt")
    ReceiptModel createReceipt(@RequestBody ReceiptDto receiptDto);

    @ApiOperation("Update receipt")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/updateReceipt/{id}")
    ReceiptModel updateReceipt(@PathVariable Long id, @RequestBody ReceiptDto receiptDto);

    @ApiOperation("Delete receipt")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> deleteReceipt(@PathVariable Long id);

    @ApiOperation("Get all receipts")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    List<ReceiptModel> getAllReceipts();

    @ApiOperation("Add product to receipt")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/addProduct/{receiptId}")
    ReceiptModel addProductToReceipt(@PathVariable Long receiptId, @RequestBody ProductInReceiptDto productInReceiptDto);

    @ApiOperation("Delete product from receipt")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/deleteProduct")
    ReceiptModel deleteProductFromReceipt(Integer productCode, ReceiptDto receiptDto);


}
