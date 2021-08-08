package com.epam.springcashmachine.repository;

//import com.epam.springcashmachine.model.ProductInReceipt;
import com.epam.springcashmachine.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

//    void addProductToReceipt(ProductInReceipt productInReceipt);

}
