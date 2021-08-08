package com.epam.springcashmachine.repository;

import com.epam.springcashmachine.model.Product;
import com.epam.springcashmachine.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> getProductByName(String name);

}
