package com.project.PriceComparatorMarket.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceHistoryDto {
    private LocalDate date;
    private double price;
    private String store;
}
