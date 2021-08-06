package com.epam.springcashmachine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Product product;

    private Long quantity;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private Receipt receipt;

}
