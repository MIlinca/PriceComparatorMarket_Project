package com.project.PriceComparatorMarket.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private double packageQuantity;
    @Column(nullable = false)
    private String packageUnit;
}
