package com.project.PriceComparatorMarket.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name ="product_id",nullable = false)
    private Product product;

    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private String currency;

    @ManyToOne
    @JoinColumn(name ="store_id",nullable = false)
    private Store store;

    @Column(nullable = false)
    private LocalDate date;
}
