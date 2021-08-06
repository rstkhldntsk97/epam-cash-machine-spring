package com.epam.springcashmachine.model;

import com.epam.springcashmachine.dto.ProductInReceiptDto;
import com.epam.springcashmachine.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer total;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany
    @JoinTable()
    private Set<ProductInReceipt> products;

}
