package com.epam.springcashmachine.model;

import com.epam.springcashmachine.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long total;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ElementCollection
    @CollectionTable(name = "receipt_has_product", joinColumns = @JoinColumn(name = "receipt_id"))
    @Column(name = "product_amount")
    @MapKeyJoinColumn(name = "product_id")
    private Map<Product, Long> products;

}
