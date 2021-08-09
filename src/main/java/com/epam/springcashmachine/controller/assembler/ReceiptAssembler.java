package com.epam.springcashmachine.controller.assembler;

import com.epam.springcashmachine.controller.ReceiptController;
import com.epam.springcashmachine.controller.UserController;
import com.epam.springcashmachine.controller.model.ReceiptModel;
import com.epam.springcashmachine.dto.ReceiptDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ReceiptAssembler extends RepresentationModelAssemblerSupport<ReceiptDto, ReceiptModel> {

    public static final String GET_RECEIPT = "get_receipt";
    public static final String CREATE_RECEIPT = "create_receipt";
    public static final String UPDATE_RECEIPT = "update_receipt";
    public static final String DELETE_RECEIPT = "delete_receipt";
    public static final String GET_ALL_RECEIPTS = "get_all_receipt";

    public ReceiptAssembler() {
        super(ReceiptController.class, ReceiptModel.class);
    }

    @Override
    public ReceiptModel toModel(ReceiptDto entity) {
        ReceiptModel receiptModel = new ReceiptModel(entity);

        Link get = linkTo(methodOn(ReceiptController.class).getReceipt(entity.getId())).withRel(GET_RECEIPT);
        Link create = linkTo(methodOn(ReceiptController.class).createReceipt(entity)).withRel(CREATE_RECEIPT);
        Link update = linkTo(methodOn(ReceiptController.class).updateReceipt(entity.getId(), entity)).withRel(UPDATE_RECEIPT);
        Link delete = linkTo(methodOn(ReceiptController.class).deleteReceipt(entity.getId())).withRel(DELETE_RECEIPT);
        Link getAll = linkTo(methodOn(ReceiptController.class).getAllReceipts()).withRel(GET_ALL_RECEIPTS);

        receiptModel.add(get, create, update,delete, getAll);

        return receiptModel;
    }

}
