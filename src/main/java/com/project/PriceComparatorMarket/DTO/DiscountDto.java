package com.project.PriceComparatorMarket.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountDto {
    private String product;
    private String store;
    private int percentageOfDiscount;
    private LocalDate createdAt;
}
