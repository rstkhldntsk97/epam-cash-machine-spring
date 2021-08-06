package com.epam.springcashmachine.repository;

import com.epam.springcashmachine.model.Product;
import com.epam.springcashmachine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product getProductByName(String name);

}
